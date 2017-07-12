package examples.database.impl;

import examples.database.api.DatabaseActionResult;

import java.util.Collection;
import java.util.Collections;

public class VoidDatabaseActionResult implements DatabaseActionResult<Void> {
	@Override public boolean isSuccessful() {
		return true;
	}

	@Override public Collection<String> getErrorMessages() {
		return Collections.emptyList();
	}

	@Override public Void getResult() {
		return null;
	}
}
