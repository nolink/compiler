package snow.token.impl;

import snow.token.Token;

public class StrToken extends Token {

	private String literal;
	
	public StrToken(int line, String str) {
		super(line);
		literal = str;
	}
	
	@Override
	public boolean isString() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return literal;
	}

}
