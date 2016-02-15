package snow.ast;

import snow.ast.BlockStmt;
import snow.ast.ParameterList;
import snow.env.Environment;
import snow.env.NestedEnv;

public class Function {
	
	protected ParameterList parameters;
	protected BlockStmt body;
	protected Environment env;
	
	public Function(ParameterList parameters, BlockStmt body, Environment env){
		this.parameters = parameters;
		this.body = body;
		this.env = env;
	}
	
	public ParameterList parameters(){return parameters;}
	public BlockStmt body(){return body;}
	public Environment makeEnv(){return new NestedEnv(env);}
	
	@Override
	public String toString() {
		return "<fun:"+  hashCode() + ">";
	}
	
}
