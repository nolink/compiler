package snow.exceptions;

import snow.ast.ASTree;

public class SnowException extends RuntimeException {

	public SnowException(String string, ASTree astListEx) {
		super(string+ " "+ astListEx.location());
	}

	public SnowException(String str){
		super(str);
	}
	
	

}
