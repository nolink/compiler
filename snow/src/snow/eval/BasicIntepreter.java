package snow.eval;

import snow.CodeDialog;
import snow.Lexer;
import snow.ast.ASTree;
import snow.ast.NullStmt;
import snow.env.Environment;
import snow.env.impl.BasicEnv;
import snow.exceptions.ParseException;
import snow.parser.BasicParser;
import snow.token.Token;

public class BasicIntepreter {
	public static void main(String[] args) throws ParseException {
		run(new BasicParser(), new BasicEnv());
	}
	public static void run(BasicParser parser, Environment env) throws ParseException{
		Lexer lexer = new Lexer(new CodeDialog());
		while(lexer.peek(0) != Token.EOF){
			ASTree t = parser.parse(lexer);
			if(!(t instanceof NullStmt)){
				Object r = ((BasicEvaluator.ASTreeEx)t).eval(env);
				System.out.println("=> " + r);
			}
		}
	}
}
