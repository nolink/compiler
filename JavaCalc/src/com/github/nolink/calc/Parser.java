package com.github.nolink.calc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.github.nolink.calc.ast.ASTBinary;
import com.github.nolink.calc.ast.ASTNumber;
import com.github.nolink.calc.ast.ASTOp;
import com.github.nolink.calc.ast.ASTree;
import com.github.nolink.calc.token.NumToken;
import com.github.nolink.calc.token.OpToken;
import com.github.nolink.calc.token.Token;

public class Parser {

	Operators ops = new Operators();

	public Parser() {
		ops.add("+", 1, true);
		ops.add("-", 1, true);
		ops.add("*", 2, true);
		ops.add("/", 2, true);
	}

	public ASTree parse(Lexer lexer) throws ParseException {

		Token t = lexer.parse();

		if (t == Token.EOF || !t.isNumber()) {
			throw new ParseException("The first token must be number", 1);
		}

		ASTree left = new ASTNumber(t);

		Token op = null;

		while ((op = nextOp(lexer)) != Token.EOF) {
			//pop it
			lexer.parse();
			left = shift(lexer, left, op);
		}

		return left;
	}

	public ASTree shift(Lexer lexer, ASTree left, Token leftOpToken) throws ParseException {
		ASTree right = new ASTNumber(nextNum(lexer));
		
		Token op = null;
		if ((op = nextOp(lexer)) != Token.EOF
				&& ops.get(op.getText()).getPriotity() >= ops.get(
						leftOpToken.getText()).getPriotity()) {
			//pop it
			lexer.parse();
			right = shift(lexer, right, op);
		}

		List<ASTree> binary = new ArrayList<ASTree>();
		binary.add(left);
		binary.add(new ASTOp(leftOpToken));
		binary.add(right);

		return new ASTBinary(binary);

	}

	private Token nextOp(Lexer lexer) throws ParseException {

		Token t = lexer.peek();

		return t;
	}

	private Token nextNum(Lexer lexer) throws ParseException {
		Token t = lexer.parse();

		return t;
	}

}
