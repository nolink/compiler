package snow.parser;

import static snow.parser.Parser.rule;
import snow.ast.Fun;

public class ClosureParser extends FuncParser {
	public ClosureParser() {
		primary.insertChoice(rule(Fun.class).sep("fun").ast(paramList).ast(block));
	}
}
