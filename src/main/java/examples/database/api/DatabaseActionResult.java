package examples.database.api;

import java.util.Collection;
import java.util.Optional;

public interface DatabaseActionResult<T> {
	boolean isSuccessful();

	Collection<String> getErrorMessages();

	T getResult();
}
