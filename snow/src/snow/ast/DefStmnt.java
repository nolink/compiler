package snow.ast;

import java.util.List;

public class DefStmnt extends ASTList {

	public DefStmnt(List<ASTree> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	public String name() {
		return ((ASTLeaf) child(0)).token().getText();
	}

	public ParameterList parameters() {
		return (ParameterList) child(1);
	}

	public BlockStmt body() {
		return (BlockStmt) child(2);
	}

	@Override
	public String toString() {
		return "(def " + name() + " " + parameters() + " " + body() + ")";
	}

}
