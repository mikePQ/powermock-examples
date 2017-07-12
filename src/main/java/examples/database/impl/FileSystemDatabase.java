package examples.database.impl;

import examples.database.api.Database;
import examples.database.api.DatabaseActionResult;
import examples.database.api.Identifiable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;

public class FileSystemDatabase<T extends Identifiable> implements Database<T> {
	private final Path databaseDir;

	public FileSystemDatabase(Path databaseDir) {
		this.databaseDir = databaseDir;
		//initDatabaseDir();
	}

	@Override public DatabaseActionResult<T> getById(String id) {
		return null;
	}

	@Override public DatabaseActionResult<Collection<T>> getByPredicate(
			Predicate<T> predicate) {
		return null;
	}

	@Override public DatabaseActionResult<Void> add(T toAdd) {
		return null;
	}

	@Override public DatabaseActionResult<Void> addAll(Collection<T> toAdd) {
		return null;
	}

	@Override public DatabaseActionResult<Void> delete(String id) {
		return null;
	}

	@Override public DatabaseActionResult<Void> deleteByPredicate(
			Predicate<T> predicate) {
		return null;
	}

	private void initDatabaseDir() {
		if (Files.exists(databaseDir)) {
			FileSystemUtils.deleteDirRecursively(databaseDir);
		}

		FileSystemUtils.createDirectory(databaseDir);
	}
}
