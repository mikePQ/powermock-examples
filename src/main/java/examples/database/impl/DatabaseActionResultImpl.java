package examples.database.impl;

import examples.database.api.DatabaseActionResultWithValue;

public class DatabaseActionResultImpl<T> implements DatabaseActionResultWithValue<T> {
	private final Exception exceptionThrown;
	private final T value;

	DatabaseActionResultImpl(T value, Exception exceptionThrown) {
		this.value = value;
		this.exceptionThrown = exceptionThrown;
	}

	@Override public boolean isSuccessful() {
		return exceptionThrown == null;
	}

	@Override public String getErrorMessage() {
		return exceptionThrown != null ? exceptionThrown.getMessage() : "";
	}

	@Override public T getValue() {
		return value;
	}
}
