package com.github.nolink.calc.token;

public abstract class Token {

	public static final String EOL = "\\n";
	public static final Token EOF = new Token(-1) {
	};

	private int lineNumber;

	public Token(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public int getLineNumber(){
		return lineNumber;
	}

	public boolean isNumber() {
		return false;
	}

	public boolean isOp() {
		return false;
	}

	public int getNumber() {
		throw new NumberFormatException();
	};

	public String getText() {
		return "";
	}

}
