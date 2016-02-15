package com.github.nolink.calc.token;

public class OpToken extends Token {

	private String value;
	
	public OpToken(int lineNumber, String value) {
		super(lineNumber);
		this.value = value;
	}
	
	@Override
	public boolean isOp() {
		return true;
	}
	
	@Override
	public String getText() {
		return value;
	}

}
