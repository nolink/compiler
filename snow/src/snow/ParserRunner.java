package snow;

import snow.ast.ASTree;
import snow.exceptions.ParseException;
import snow.parser.BasicParser;
import snow.token.Token;

public class ParserRunner {
	public static void main(String[] args) throws ParseException {
		Lexer lexer = new Lexer(new CodeDialog());
		BasicParser parser = new BasicParser();
		while(lexer.peek(0) != Token.EOF){
			ASTree ast = parser.parse(lexer);
			System.out.println("=> "+ ast.toString());
		}
	}
}
