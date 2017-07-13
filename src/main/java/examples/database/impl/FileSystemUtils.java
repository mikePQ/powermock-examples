package examples.database.impl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSystemUtils {

	private FileSystemUtils() {
	}

	public static void createDirectory(Path dir) {
		ignoreIOExceptions(() -> Files.createDirectories(dir));
	}

	public static void createFile(Path file) {
		ignoreIOExceptions(() -> Files.createFile(file));
	}

	public static void deleteFile(String file, Path parent) throws IOException {
		Files.delete(Paths.get(parent.toString(), file));
	}

	public static boolean exists(String file, Path parent) {
		return Files.exists(Paths.get(parent.toString(), file));
	}

	public static String readFile(String file, Path parent) throws IOException {
		return readFile(Paths.get(parent.toString(), file));
	}

	public static String readFile(Path file) {
		try {
			byte[] bytes = Files.readAllBytes(file);
			return new String(bytes);
		}
		catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static DirectoryStream<Path> getAllFiles(Path directory) throws IOException {
		return Files.newDirectoryStream(directory);
	}

	public static void writeFile(Path elemFile, List<String> strings) throws IOException {
		Files.write(elemFile, strings);
	}

	private static void ignoreIOExceptions(IOExecutable executable) {
		try {
			executable.execute();
		}
		catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@FunctionalInterface interface IOExecutable {
		void execute() throws IOException;
	}
}
