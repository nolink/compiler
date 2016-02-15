package com.github.nolink.calc.ast;

import java.util.List;

public class ASTBinary extends ASTList {

	public ASTBinary(List<ASTree> trees) {
		super(trees);
		// TODO Auto-generated constructor stub
	}
	
	public ASTree left(){
		return child(0);
	}
	
	public ASTree op(){
		return child(1);
	}
	
	public ASTree right(){
		return child(2);
	}
	
	

}
