package com.github.nolink.calc.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ASTList extends ASTree {
	
	protected List<ASTree> trees = new ArrayList<ASTree>();
	
	public ASTList(List<ASTree> trees) {
		this.trees = trees;
	}

	@Override
	public ASTree child(int i) {
		return trees.get(i);
	}

	@Override
	public Iterator<ASTree> children() {
		return trees.iterator();
	}

	@Override
	public int numChildren() {
		return trees.size();
	}

	@Override
	public String location() {
		for(ASTree e : trees){
			String result = e.location();
			if(null != result){
				return result;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb  =new StringBuilder();
		sb.append('(');
		String sep = "";
		for(ASTree t: trees){
			sb.append(sep);
			sep = " ";
			sb.append(t.toString());
		}
		return sb.append(')').toString();
	}

	@Override
	public int eval() {
		throw new NotImplementedException();
	}

}
