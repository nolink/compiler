package snow.token.impl;

import snow.token.Token;

public class NumberToken extends Token {
	
	private int intValue;
	public NumberToken(int line, int v){
		super(line);
		intValue = v;
	}
	
	@Override
	public boolean isNumber() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return intValue;
	}
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return String.valueOf(intValue);
	}

}
