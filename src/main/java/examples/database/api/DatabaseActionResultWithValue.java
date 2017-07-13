package examples.database.api;

public interface DatabaseActionResultWithValue<T> extends DatabaseActionResult {
	T getValue();
}
