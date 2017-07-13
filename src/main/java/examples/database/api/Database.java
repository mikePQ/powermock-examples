package examples.database.api;

import java.util.Collection;
import java.util.function.Predicate;

public interface Database<T extends Identifiable> {
	DatabaseActionResultWithValue<T> getById(String id);

	DatabaseActionResultWithValue<Collection<T>> getByPredicate(Predicate<T> predicate);

	DatabaseActionResult add(T toAdd);

	DatabaseActionResult addAll(Collection<T> toAdd);

	DatabaseActionResult delete(String id);
}
