package examples.filesystem;

import java.io.File;

public interface FileSystemListener {
	void fileChanged(File file);

	void fileCreated(File file);

	void fileDeleted(File file);
}
