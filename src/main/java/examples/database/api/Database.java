package examples.database.api;

import java.util.Collection;
import java.util.function.Predicate;

public interface Database<T> {
	DatabaseActionResult<T> getById(String id);

	DatabaseActionResult<Collection<T>> getByPredicate(Predicate<T> predicate);

	DatabaseActionResult<Void> add(T toAdd);

	DatabaseActionResult<Void> addAll(Collection<T> toAdd);

	DatabaseActionResult<Void> delete(String id);

	DatabaseActionResult<Void> deleteByPredicate(Predicate<T> predicate);
}
