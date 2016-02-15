package snow.eval;

import javassist.gluonj.util.Loader;

public class FuncRunner {
	public static void main(String[] args) throws Throwable {
		Loader.run(FuncIntepreter.class, args, FuncEvaluator.class);
	}
}
