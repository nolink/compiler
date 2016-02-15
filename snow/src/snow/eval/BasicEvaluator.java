package snow.eval;

import java.util.List;

import javassist.gluonj.Reviser;
import snow.ast.ASTLeaf;
import snow.ast.ASTList;
import snow.ast.ASTree;
import snow.ast.BinaryExpr;
import snow.ast.BlockStmt;
import snow.ast.IfStmt;
import snow.ast.Name;
import snow.ast.NegativeExpr;
import snow.ast.NullStmt;
import snow.ast.NumberLiteral;
import snow.ast.StringLiteral;
import snow.ast.WhileStmt;
import snow.env.Environment;
import snow.exceptions.SnowException;
import snow.token.Token;

@Reviser
public class BasicEvaluator {

	public static final int TRUE = 1;
	public static final int FALSE = 0;

	@Reviser
	public static abstract class ASTreeEx extends ASTree {
		public abstract Object eval(Environment env);
	}

	@Reviser
	public static class ASTListEx extends ASTList {

		public ASTListEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			throw new SnowException("can not eval: " + toString(), this);
		}

	}

	@Reviser
	public static class ASTLeafEx extends ASTLeaf {

		public ASTLeafEx(Token t) {
			super(t);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			throw new SnowException("can not eval: " + toString(), this);
		}

	}

	@Reviser
	public static class NumberEx extends NumberLiteral {

		public NumberEx(Token t) {
			super(t);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			return value();
		}

	}

	@Reviser
	public static class StringEx extends StringLiteral {

		public StringEx(Token t) {
			super(t);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			return value();
		}

	}

	@Reviser
	public static class NameEx extends Name {

		public NameEx(Token t) {
			super(t);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			Object val = env.get(name());
			if (null == val) {
				throw new SnowException("Undefined name: " + name(), this);
			}
			return val;
		}

	}

	@Reviser
	public static class NegativeEx extends NegativeExpr {

		public NegativeEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		public Object eval(Environment env) {
			Object val = ((ASTreeEx) operand()).eval(env);
			if (val instanceof Integer) {
				return new Integer(-((Integer)val).intValue());
			} else {
				throw new SnowException("bad type for -: " + this);
			}
		}

	}

	@Reviser
	public static class BinaryEx extends BinaryExpr {

		public BinaryEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public Object eval(Environment env){
			String op = operator();
			if("=".equals(op)){
				Object right = ((ASTreeEx)right()).eval(env);
				return computeAssign(env, right);
			}else{
				Object left = ((ASTreeEx)left()).eval(env);
				Object right = ((ASTreeEx)right()).eval(env);
				return computeOp(left, op, right);
			}
		}

		protected Object computeAssign(Environment env, Object rvalue) {
			ASTree left = left();
			if (left instanceof Name) {
				env.put(((Name) left).name(), rvalue);
				return rvalue;
			} else {
				throw new SnowException("bad assignment", this);
			}
		}

		protected Object computeOp(Object left, String op, Object right) {
			if(left instanceof Integer && right instanceof Integer){
				return computerNumber((Integer)left, op, (Integer)right);
			}else{
				if(op.equals("+")){
					return String.valueOf(left) + String.valueOf(right);
				}else if(op.equals("==")){
					if(null == left){
						return right == null ? TRUE : FALSE;
					}else{
						return left.equals(right) ? TRUE : FALSE;
					}
				}else{
					throw new SnowException("bad type", this);
				}
			}
		}

		protected Object computerNumber(Integer left, String op, Integer right) {
			int a = left.intValue();
			int b = right.intValue();
			if (op.equals("+")) {
				return a + b;
			} else if (op.equals("-")) {
				return a - b;
			} else if (op.equals("*")) {
				return a * b;
			} else if (op.equals("/")) {
				return a / b;
			} else if (op.equals("%")) {
				return a % b;
			} else if (op.equals(">")) {
				return a > b ? TRUE : FALSE;
			} else if (op.equals("<")) {
				return a < b ? TRUE : FALSE;
			} else if(op.equals("==")){
				return a == b? TRUE : FALSE;
			}else{
				throw new SnowException("Bad operator ", this);
			}
		}

	}
	
	@Reviser
	public static class BlockEx extends BlockStmt{

		public BlockEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public Object eval(Environment env){
			Object result = 0;
			for(ASTree t : this){
				if(!(t instanceof NullStmt)){
					result = ((ASTreeEx)t).eval(env);
				}
			}
			return result;
		}
		
	}
	
	@Reviser
	public static class IfEx extends IfStmt{

		public IfEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public Object eval(Environment env){
			Object c = ((ASTreeEx)condition()).eval(env);
			if(c instanceof Integer && ((Integer)c).intValue() != FALSE){
				return ((ASTreeEx)thenBlock()).eval(env);
			}else{
				ASTree b = elseBolck();
				if(b == null){
					return 0;
				}else{
					return ((ASTreeEx)b).eval(env);
				}
			}
		}
	}
	
	@Reviser
	public static class WhileEx extends WhileStmt{

		public WhileEx(List<ASTree> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}
		
		public Object eval(Environment env){
			Object result = 0;
			for(;;){
				Object c = ((ASTreeEx)condition()).eval(env);
				if(c instanceof Integer && ((Integer)c).intValue() == FALSE){
					return result;
				}else{
					result = ((ASTreeEx)body()).eval(env);
				}
			}
		}
		
	}
	

}
