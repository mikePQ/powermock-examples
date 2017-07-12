package examples.library;

import examples.database.impl.FileSystemDatabase;
import examples.database.objects.FileRepresentationToObjectMapper;
import examples.database.objects.ObjectToFileRepresentationMapper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BookDatabase extends FileSystemDatabase<Book> {
	private static final String DATABASE_DIR = System.getProperty("file.database.dir");
	private static final BookDatabase INSTANCE = new BookDatabase(
			Paths.get(DATABASE_DIR));

	private BookDatabase(Path databaseDir) {
		super(databaseDir, new ObjectToFileRepresentationMapper<>(),
				new FileRepresentationToObjectMapper<>(Book.class));
	}

	public static BookDatabase get() {
		return INSTANCE;
	}
}
