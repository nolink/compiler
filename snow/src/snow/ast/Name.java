package snow.ast;

import snow.token.Token;

public class Name extends ASTLeaf {

	public Name(Token t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	
	public String name(){return token().getText();}

}
