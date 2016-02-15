package snow.env;

import java.util.HashMap;

import snow.eval.FuncEvaluator.EnvEx;

public class NestedEnv implements Environment {

	protected HashMap<String, Object> values;
	protected Environment outer;

	public NestedEnv(Environment e) {
		values = new HashMap<>();
		outer = e;
	}

	public NestedEnv() {
		this(null);
	}

	public void setOuter(Environment e) {
		outer = e;
	}
	
	public void putNew(String name, Object value){
		values.put(name, value);
	}
	
	public Environment where(String name){
		if(values.get(name) != null){
			return this;
		}else if(outer  == null){
			return null;
		}else{
			return ((EnvEx)outer).where(name);
		}
	}

	@Override
	public void put(String name, Object value) {
		Environment e = where(name);
		if(e == null){
			e = this;
		}
		((EnvEx)e).putNew(name, value);
	}

	@Override
	public Object get(String name) {
		Object v = values.get(name);
		if(null == v & outer != null){
			return outer.get(name);
		}else{
			return v;
		}
	}

}
