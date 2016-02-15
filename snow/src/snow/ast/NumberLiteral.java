package snow.ast;

import java.util.Iterator;

import snow.token.Token;

public class NumberLiteral extends ASTLeaf {

	public NumberLiteral(Token t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	
	public int value(){return token().getNumber();}

}
