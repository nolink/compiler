package snow.eval;

import java.util.List;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import snow.ast.ASTree;
import snow.ast.Arguments;
import snow.ast.DefStmnt;
import snow.ast.Function;
import snow.ast.ParameterList;
import snow.ast.Postfix;
import snow.ast.PrimaryExpr;
import snow.env.Environment;
import snow.eval.BasicEvaluator.ASTreeEx;
import snow.eval.BasicEvaluator.BlockEx;
import snow.exceptions.SnowException;

@Require(BasicEvaluator.class)
@Reviser
public class FuncEvaluator {

	@Reviser
	public static interface EnvEx extends Environment {
		void putNew(String name, Object value);

		Environment where(String name);

		void setOuter(Environment e);
	}

	@Reviser
	public static class DefStmntEx extends DefStmnt {

		public DefStmntEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			((EnvEx) env).putNew(name(), new Function(parameters(), body(), env));
			return name();
		}

	}

	@Reviser
	public static class PrimaryEx extends PrimaryExpr {

		public PrimaryEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		public ASTree operand() {
			return child(0);
		}
		
		public Postfix postfix(int nest){
			return (Postfix)child(numChildren() - nest - 1);
		}
		public boolean hasPostfix(int nest){
			return numChildren() - nest > 1;
		}
		public Object eval(Environment env){
			return evalSubExpr(env, 0);
		}
		public Object evalSubExpr(Environment env, int nest){
			if(hasPostfix(nest)){
				Object target = evalSubExpr(env, nest + 1);
				return ((PostfixEx)postfix(nest)).eval(env, target);
			}else{
				return ((ASTreeEx)operand()).eval(env);
			}
		}

	}
	
	@Reviser
	public static abstract class PostfixEx extends Postfix{

		public PostfixEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public abstract Object eval(Environment env, Object value);
		
	}
	
	@Reviser
	public static class ArgumentsEx extends Arguments{

		public ArgumentsEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public Object eval(Environment callerEnv, Object value){
			if(!(value instanceof Function)){
				throw new SnowException("bad function", this);
			}
			Function func = (Function)value;
			ParameterList params = func.parameters();
			if(size() != params.size()){
				throw new SnowException("bad number of arguments", this);
			}
			Environment newEnv = func.makeEnv();
			int num = 0;
			for(ASTree e: this){
				((ParamsEx)params).eval(newEnv, num++, ((ASTreeEx)e).eval(callerEnv));
			}
			return ((BlockEx)func.body()).eval(newEnv);
		}
		
	}
	
	@Reviser
	public static class ParamsEx  extends ParameterList{

		public ParamsEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public void eval(Environment env, int index, Object value){
			((EnvEx)env).putNew(name(index), value);
		}
		
	}

}
