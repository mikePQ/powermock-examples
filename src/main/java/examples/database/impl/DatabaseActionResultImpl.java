package examples.database.impl;

import examples.database.api.DatabaseActionResultWithValue;

import java.util.Collection;
import java.util.Collections;

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

	@Override public Collection<String> getErrorMessages() {
		return exceptionThrown != null ?
				Collections.singletonList(exceptionThrown.getMessage()) :
				Collections.emptyList();
	}

	@Override public T getValue() {
		return value;
	}
}
