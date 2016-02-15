package snow.eval;

import snow.env.NestedEnv;
import snow.exceptions.ParseException;
import snow.parser.ClosureParser;

public class NativeInterpreter extends BasicIntepreter {

	public static void main(String[] args) throws ParseException {
		run(new ClosureParser(), new Natives().environment(new NestedEnv()));
	}

}
