package com.github.nolink.calc.token;

public class NumToken extends Token {
	
	private int value;

	public NumToken(int lineNumber, int value) {
		super(lineNumber);
		this.value = value;
	}

	@Override
	public int getNumber() {
		return value;
	}

	@Override
	public boolean isNumber() {
		return true;
	}
	
	@Override
	public String getText() {
		return String.valueOf(value);
	}

}
