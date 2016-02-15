package snow.eval;

import javassist.gluonj.util.Loader;

public class BasicRunner {
	public static void main(String[] args) throws Throwable {
		Loader.run(BasicIntepreter.class, args, BasicEvaluator.class);
	}
}
