package snow;

import snow.exceptions.ParseException;
import snow.token.Token;

public class LexerRunner {
	public static void main(String[] args) throws ParseException {
		Lexer lexer = new Lexer(new CodeDialog());
		for(Token t;(t = lexer.read()) != Token.EOF;){
			System.out.println("=> "+ t.getText());
		}
	}
}
