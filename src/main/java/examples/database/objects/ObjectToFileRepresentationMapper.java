package examples.database.objects;

import com.google.gson.Gson;

public class ObjectToFileRepresentationMapper<T> {
	private final Gson gson;

	public ObjectToFileRepresentationMapper() {
		this.gson = new Gson();
	}

	public String map(T object) {
		return gson.toJson(object);
	}
}
