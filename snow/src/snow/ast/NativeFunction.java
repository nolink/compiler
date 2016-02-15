package snow.ast;

import java.lang.reflect.Method;

import snow.exceptions.SnowException;

public class NativeFunction {

	protected Method method;
	protected String name;
	protected int numParams;
	
	public NativeFunction(String n, Method m){
		name = n;
		method = m;
		numParams = m.getParameterTypes().length;
	}
	
	@Override
	public String toString() {
		return "<native:" + hashCode() + ">";
	}

	public int numOfParameters(){
		return numParams;
	}
	
	public Object invoke(Object[] args, ASTree tree){
		try{
			return method.invoke(null, args);
		}catch(Exception ex){
			throw new SnowException("bad native function call: "+name, tree);
		}
	}
	
}
