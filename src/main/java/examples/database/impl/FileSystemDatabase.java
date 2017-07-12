package examples.database.impl;

import examples.database.api.Database;
import examples.database.api.DatabaseActionResult;

import java.util.Collection;
import java.util.function.Predicate;

public class FileSystemDatabase<T> implements Database<T> {

	@Override public DatabaseActionResult<T> getById(String id) {
		return null;
	}

	@Override public DatabaseActionResult<Collection<T>> getByPredicate(
			Predicate<T> predicate) {
		return null;
	}

	@Override public DatabaseActionResult<Void> add(T toAdd) {
		return null;
	}

	@Override public DatabaseActionResult<Void> addAll(Collection<T> toAdd) {
		return null;
	}

	@Override public DatabaseActionResult<Void> delete(String id) {
		return null;
	}

	@Override public DatabaseActionResult<Void> deleteByPredicate(
			Predicate<T> predicate) {
		return null;
	}
}
