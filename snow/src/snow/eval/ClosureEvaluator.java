package snow.eval;

import java.util.List;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import snow.ast.ASTree;
import snow.ast.Fun;
import snow.ast.Function;
import snow.env.Environment;

@Require(FuncEvaluator.class)
@Reviser
public class ClosureEvaluator {

	@Reviser
	public static class FunEx extends Fun{

		public FunEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public Object eval(Environment env){
			return new Function(parameters(), body(), env);
		}
		
	}
	
}
