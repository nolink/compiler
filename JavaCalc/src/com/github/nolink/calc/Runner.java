package com.github.nolink.calc;

import java.text.ParseException;

import com.github.nolink.calc.ast.ASTree;

public class Runner {

	public static void main(String[] args) throws ParseException {
		Lexer lex = new Lexer(new CodeDialog());
		Parser parse = new Parser();
		ASTree tree = parse.parse(lex);
		
		System.out.println(tree.numChildren());
		
	}
}
