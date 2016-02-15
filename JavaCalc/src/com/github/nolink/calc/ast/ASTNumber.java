package com.github.nolink.calc.ast;

import com.github.nolink.calc.token.Token;

public class ASTNumber extends ASTLeaf {

	public ASTNumber(Token t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public int value() {
		return token.getNumber();
	}
	
	@Override
	public int eval() {
		return value();
	}

}
