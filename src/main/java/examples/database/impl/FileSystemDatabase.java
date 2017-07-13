package examples.database.impl;

import examples.database.api.Database;
import examples.database.api.DatabaseActionResult;
import examples.database.api.DatabaseActionResultWithValue;
import examples.database.api.Identifiable;
import examples.database.objects.FileRepresentationToObjectMapper;
import examples.database.objects.ObjectToFileRepresentationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileSystemDatabase<T extends Identifiable> implements Database<T> {
	private static final Logger logger = LoggerFactory.getLogger(FileSystemDatabase.class);

	private final Path databaseDir;
	private final ObjectToFileRepresentationMapper<T> objectToFileRepresentationMapper;
	private final FileRepresentationToObjectMapper<T> fileRepresentationToObjectMapper;

	public FileSystemDatabase(Path databaseDir,
			ObjectToFileRepresentationMapper<T> objectToFileRepresentationMapper,
			FileRepresentationToObjectMapper<T> fileRepresentationToObjectMapper) {

		this.databaseDir = databaseDir;
		this.objectToFileRepresentationMapper = objectToFileRepresentationMapper;
		this.fileRepresentationToObjectMapper = fileRepresentationToObjectMapper;
		initDatabaseDir();
	}

	@Override public DatabaseActionResultWithValue<T> getById(String id) {
		try {
			return new DatabaseActionResultImpl<>(getElemById(id), null);
		}
		catch (Exception e) {
			return new DatabaseActionResultImpl<>(null, e);
		}
	}

	@Override public DatabaseActionResultWithValue<Collection<T>> getByPredicate(
			Predicate<T> predicate) {
		try {
			return new DatabaseActionResultImpl<>(getElemsByPredicate(predicate), null);
		}
		catch (Exception e) {
			return new DatabaseActionResultImpl<>(null, e);
		}
	}

	@Override public DatabaseActionResult add(T toAdd) {
		try {
			addElem(toAdd);
			return new VoidDatabaseActionResult();
		}
		catch (Exception e) {
			return new DatabaseActionResultImpl<>(null, e);
		}
	}

	@Override public DatabaseActionResult addAll(Collection<T> toAdd) {
		try {
			addAllElems(toAdd);
			return new VoidDatabaseActionResult();
		}
		catch (Exception e) {
			return new DatabaseActionResultImpl<>(null, e);
		}
	}

	@Override public DatabaseActionResult delete(String id) {
		try {
			deleteElemById(id);
			return new VoidDatabaseActionResult();
		}
		catch (Exception e) {
			return new DatabaseActionResultImpl<>(null, e);
		}
	}

	private void initDatabaseDir() {
		if (!Files.exists(databaseDir)) {
			FileSystemUtils.createDirectory(databaseDir);
		}
	}

	private T getElemById(String id) throws Exception {
		if (!FileSystemUtils.exists(id, databaseDir)) {
			throw new FileNotFoundException("Cannot find file for id: " + id);
		}

		String fileContents = FileSystemUtils.readFile(id, databaseDir);
		return fileRepresentationToObjectMapper.map(fileContents);
	}

	private Collection<T> getElemsByPredicate(Predicate<T> predicate) throws Exception {
		Collection<T> result = new ArrayList<>();
		FileSystemUtils.getAllFiles(databaseDir)
				.forEach(path -> {
					String fileContents = FileSystemUtils.readFile(path);
					result.add(fileRepresentationToObjectMapper.map(fileContents));
				});

		return result.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}

	private void addElem(T elem) throws Exception {
		Path elemFile = Paths.get(databaseDir.toString(), elem.getId());
		FileSystemUtils.createFile(elemFile);
		FileSystemUtils.writeFile(elemFile,
				Collections.singletonList(objectToFileRepresentationMapper.map(elem)));
	}

	private void addAllElems(Collection<T> elems) {
		elems.forEach(e -> {
			try {
				addElem(e);
			}
			catch (Exception e1) {
				logger.warn(e1.getMessage(), e);
			}
		});
	}

	private void deleteElemById(String id) throws Exception {
		if (!FileSystemUtils.exists(id, databaseDir)) {
			return;
		}

		FileSystemUtils.deleteFile(id, databaseDir);
	}
}
