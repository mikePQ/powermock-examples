package examples.database.objects;

import com.google.gson.Gson;

public class FileRepresentationToObjectMapper<T> {
	private final Gson gson;
	private final Class<T> clazz;

	public FileRepresentationToObjectMapper(Class<T> clazz) {
		this.gson = new Gson();
		this.clazz = clazz;
	}

	public T map(String objectRepresentation) {
		return gson.fromJson(objectRepresentation, clazz);
	}

}
