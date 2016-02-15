package com.github.nolink.calc.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.github.nolink.calc.token.Token;

public class ASTLeaf extends ASTree {
	
	private List<ASTree> empty = new ArrayList<ASTree>();
	
	protected Token token;
	
	public ASTLeaf(Token t) {
		token = t;
	}

	@Override
	public ASTree child(int i) {
		throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	public Iterator<ASTree> children() {
		return empty.iterator();
	}

	@Override
	public int numChildren() {
		return 0;
	}

	@Override
	public String location() {
		return "at line: " + token.getLineNumber();
	}
	
	@Override
	public String toString() {
		return token.getText();
	}
	
	public Token getToken(){
		return token;
	}

	@Override
	public int eval() {
		throw new NotImplementedException();
	}

}
