package snow.parser;

import static snow.parser.Parser.rule;

import java.util.HashSet;

import snow.Lexer;
import snow.ast.ASTree;
import snow.ast.BinaryExpr;
import snow.ast.BlockStmt;
import snow.ast.IfStmt;
import snow.ast.Name;
import snow.ast.NegativeExpr;
import snow.ast.NullStmt;
import snow.ast.NumberLiteral;
import snow.ast.PrimaryExpr;
import snow.ast.StringLiteral;
import snow.ast.WhileStmt;
import snow.exceptions.ParseException;
import snow.parser.Parser.Operators;
import snow.token.Token;

public class BasicParser {

	HashSet<String> reserved = new HashSet<String>();
	Operators operators = new Operators();

	Parser expr0 = rule();

	Parser primary = rule(PrimaryExpr.class).or(rule().sep("(").ast(expr0).sep(")"), rule().number(NumberLiteral.class),
			rule().identifier(Name.class, reserved), rule().string(StringLiteral.class));

	Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary), primary);
	Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

	Parser statement0 = rule();

	Parser block = rule(BlockStmt.class).sep("{").option(statement0)
			.repeat(rule().sep(";", Token.EOL).option(statement0)).sep("}");

	Parser simple = rule(PrimaryExpr.class).ast(expr);

	Parser statement = statement0.or(
			rule(IfStmt.class).sep("if").ast(expr).ast(block).option(rule().sep("else").ast(block)),
			rule(WhileStmt.class).sep("while").ast(expr).ast(block), simple);
	
	Parser program = rule().or(statement, rule(NullStmt.class)).sep(";",Token.EOL);
	
	public BasicParser(){
		reserved.add(";");
		reserved.add("}");
		reserved.add(Token.EOL);
		
		operators.add("=", 1, Operators.RIGHT);
		operators.add("==", 2, Operators.LEFT);
		operators.add(">", 2, Operators.LEFT);
		operators.add("<", 2, Operators.LEFT);
		operators.add("+", 3, Operators.LEFT);
		operators.add("-", 3, Operators.LEFT);
		operators.add("*", 4, Operators.LEFT);
		operators.add("/", 4, Operators.LEFT);
		operators.add("%", 4, Operators.LEFT);
		
	}
	
	public ASTree parse(Lexer lexer) throws ParseException{
		return program.parse(lexer);
	}
	

}
