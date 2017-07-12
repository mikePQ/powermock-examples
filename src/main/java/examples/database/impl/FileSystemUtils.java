package examples.database.impl;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

class FileSystemUtils {

	static void deleteDirRecursively(Path dir) {
		ignoreIOExceptions(() ->
				Files.walk(dir, FileVisitOption.FOLLOW_LINKS)
				.sorted(Comparator.reverseOrder())
				.map(Path::toFile)
				.forEach(File::delete));
	}

	static void createDirectory(Path dir) {
		ignoreIOExceptions(() -> Files.createDirectory(dir));
	}

	private static void ignoreIOExceptions(IOExecutable executable) {
		try {
			executable.execute();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private FileSystemUtils() {
	}

	@FunctionalInterface
	interface IOExecutable {
		void execute() throws IOException;
	}
}
