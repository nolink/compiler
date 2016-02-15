package snow.ast;

import java.util.List;

public class IfStmt extends ASTList {

	public IfStmt(List<ASTree> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	public ASTree condition() {
		return child(0);
	}

	public ASTree thenBlock() {
		return child(1);
	}

	public ASTree elseBolck() {
		return numChildren() > 2 ? child(2) : null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "if " + condition() + " " + thenBlock() + " else " + elseBolck() + ")";
	}

}
