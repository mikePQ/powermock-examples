package examples.database.api;

import java.util.Collection;
import java.util.Optional;

public interface DatabaseActionResult {
	boolean isSuccessful();

	Collection<String> getErrorMessages();
}
