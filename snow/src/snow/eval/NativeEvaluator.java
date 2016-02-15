package snow.eval;

import java.util.List;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import snow.ast.ASTree;
import snow.ast.NativeFunction;
import snow.env.Environment;
import snow.eval.BasicEvaluator.ASTreeEx;
import snow.exceptions.SnowException;

@Require(FuncEvaluator.class)
@Reviser
public class NativeEvaluator {
	
	@Reviser
	public static class NativeArgsEx extends FuncEvaluator.ArgumentsEx{

		public NativeArgsEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Object eval(Environment callerEnv, Object value) {
			if(!(value instanceof NativeFunction)){
				return super.eval(callerEnv, value);
			}
			
			NativeFunction func = (NativeFunction)value;
			
			int nparams = func.numOfParameters();
			
			if(size() != nparams){
				throw new SnowException("bad number of arguments", this);
			}
			Object[] args = new Object[nparams];
			int num = 0;
			for(ASTree a : this){
				ASTreeEx ex = (ASTreeEx)a;
				args[num++] = ex.eval(callerEnv);
			}
			return func.invoke(args, this);
			
		}
		
	}
	
}
