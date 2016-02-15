package snow.ast;

import java.util.List;

public class WhileStmt extends ASTList {

	public WhileStmt(List<ASTree> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	public ASTree condition(){return child(0);}
	
	public ASTree body(){return child(1);}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(while " + condition() + " " + body() + ")";
	}

}
