package examples.database.impl;

import examples.database.api.DatabaseActionResult;

import java.util.Collection;
import java.util.Collections;

public class DatabaseActionResultImpl<T> implements DatabaseActionResult<T> {
	private final Exception exceptionThrown;
	private final T result;

	DatabaseActionResultImpl(T result, Exception exceptionThrown) {
		this.result = result;
		this.exceptionThrown = exceptionThrown;
	}

	@Override public boolean isSuccessful() {
		return exceptionThrown == null;
	}

	@Override public Collection<String> getErrorMessages() {
		return exceptionThrown != null ? Collections.singletonList(exceptionThrown.getMessage()) : Collections.emptyList();
	}

	@Override public T getResult() {
		return result;
	}
}
