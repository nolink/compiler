package snow.eval;

import snow.env.NestedEnv;
import snow.exceptions.ParseException;
import snow.parser.ClosureParser;

public class ClosureInterpreter extends BasicIntepreter {
	public static void main(String[] args) throws ParseException {
		run(new ClosureParser(), new NestedEnv());
	}
}
