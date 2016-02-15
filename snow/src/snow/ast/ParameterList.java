package snow.ast;

import java.util.List;

public class ParameterList extends ASTList {

	public ParameterList(List<ASTree> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	public int size(){return numChildren();}
	
	public String name(int i){
		return ((ASTLeaf)child(i)).token().getText();
	}

}
