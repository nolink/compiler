package snow.eval;

import snow.env.NestedEnv;
import snow.exceptions.ParseException;
import snow.parser.FuncParser;

public class FuncIntepreter extends BasicIntepreter {
	public static void main(String[] args) throws ParseException {
		run(new FuncParser(), new NestedEnv());
	}
}
