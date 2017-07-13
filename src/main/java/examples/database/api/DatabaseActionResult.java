package examples.database.api;

public interface DatabaseActionResult {
	boolean isSuccessful();

	String getErrorMessage();
}
