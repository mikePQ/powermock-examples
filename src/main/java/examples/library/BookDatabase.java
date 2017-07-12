package examples.library;

import examples.database.api.DatabaseActionResult;
import examples.database.impl.FileSystemDatabase;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Predicate;

public class BookDatabase extends FileSystemDatabase<Book> {
	private static final String DATABASE_DIR = System.getProperty("file.database.dir");
	private static final BookDatabase INSTANCE = new BookDatabase(
			Paths.get(DATABASE_DIR));

	private BookDatabase(Path databaseDir) {
		super(databaseDir);
	}

	public static BookDatabase get() {
		return INSTANCE;
	}

	@Override public DatabaseActionResult<Collection<Book>> getByPredicate(
			Predicate<Book> predicate) {

		return new DatabaseActionResult<Collection<Book>>() {
			@Override public boolean isSuccessful() {
				return true;
			}

			@Override public Collection<String> getErrorMessages() {
				return Collections.emptyList();
			}

			@Override public Collection<Book> getResult() {
				return Arrays.asList(new Book(UUID.randomUUID()
						.toString(), "aaaaa", "ziemniak", "asfsa", 2211), new Book(
						UUID.randomUUID()
								.toString(), "ffff", "afsafatgf", "fasfal", 1999));
			}
		};
	}
}
