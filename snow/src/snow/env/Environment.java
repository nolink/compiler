package snow.env;

public interface Environment {
	void put(String key, Object value);
	Object get(String name);
}
