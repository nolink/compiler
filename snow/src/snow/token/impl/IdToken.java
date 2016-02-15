package snow.token.impl;

import snow.token.Token;

public class IdToken extends Token {
	private String text;
	public IdToken(int line, String text) {
		super(line);
		this.text = text;
	}
	
	@Override
	public boolean isIdentifier() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

}
