package snow.ast;

import java.util.List;

public class Fun extends ASTList {

	public Fun(List<ASTree> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	public ParameterList parameters() {
		return (ParameterList) child(0);
	}

	public BlockStmt body() {
		return (BlockStmt) child(1);
	}

	@Override
	public String toString() {
		return "(fun:" + parameters() + " " + body() + ")";
	}

}
