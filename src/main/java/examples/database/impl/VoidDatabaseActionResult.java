package examples.database.impl;

import examples.database.api.DatabaseActionResult;

public class VoidDatabaseActionResult implements DatabaseActionResult {
	@Override public boolean isSuccessful() {
		return true;
	}

	@Override public String getErrorMessage() {
		return "";
	}
}
