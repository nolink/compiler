package snow.env.impl;

import java.util.HashMap;

import snow.env.Environment;

public class BasicEnv implements Environment {
	
	protected HashMap<String,Object> values = new HashMap<String, Object>();

	@Override
	public void put(String key, Object value) {
		values.put(key, value);
	}

	@Override
	public Object get(String name) {
		return values.get(name);
	}

}
