package snow.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import snow.token.Token;

public class ASTLeaf extends ASTree {

	private static List<ASTree> empty = new ArrayList<ASTree>();
	protected Token token;

	public ASTLeaf(Token t) {
		token = t;
	}

	@Override
	public ASTree child(int i) {
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int numChildren() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<ASTree> children() {
		return empty.iterator();
	}

	@Override
	public String location() {
		return "at line " + token.getLineNumber();
	}

	@Override
	public String toString() {
		return token.getText();
	}

	public Token token() {
		return token;
	}

}
