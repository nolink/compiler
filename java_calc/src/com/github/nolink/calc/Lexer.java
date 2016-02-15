package com.github.nolink.calc;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.nolink.calc.token.NumToken;
import com.github.nolink.calc.token.OpToken;
import com.github.nolink.calc.token.Token;

public class Lexer {
	private static final String pattern = "\\s*(([0-9]+)|(\\+|-|\\*|/))";
	private static final Pattern compiledPattern = Pattern.compile(pattern);

	private LineNumberReader lineNumberReader;
	private List<Token> tokens = new ArrayList<Token>();
	private boolean hasMore = true;

	public Lexer(Reader reader) {
		lineNumberReader = new LineNumberReader(reader);
	}

	public Token peek() throws ParseException {
		if (hasMore) {
			doIt();
		}
		if (tokens.size() == 0) {
			return Token.EOF;
		}
		return tokens.get(0);
	}

	public Token parse() throws ParseException {
		if (hasMore) {
			doIt();
		}
		if (tokens.size() == 0) {
			return Token.EOF;
		}
		return tokens.remove(0);
	}

	private void doIt() throws ParseException {
		if (tokens.size() == 0) {
			String line = null;
			try {
				line = lineNumberReader.readLine();
			} catch (IOException e) {
				return;
			}

			if (null == line) {
				hasMore = false;
				return;
			}

			Matcher matcher = compiledPattern.matcher(line);
			matcher.useTransparentBounds(true).useAnchoringBounds(false);

			int pos = 0;
			int endPos = line.length();

			while (pos < endPos) {
				matcher.region(pos, endPos);
				if (matcher.lookingAt()) {
					addToken(lineNumberReader.getLineNumber(), matcher);
					pos = matcher.end();
				} else {
					throw new ParseException(line, pos);
				}
			}
		}
	}

	private void addToken(int lineNum, Matcher m) {
		String group1 = m.group(1);
		if (null != group1) {
			if (null != m.group(2)) {
				// number
				tokens.add(new NumToken(lineNum, Integer.valueOf(m.group(2))));
			} else if (null != m.group(3)) {
				tokens.add(new OpToken(lineNum, m.group(3)));
			}
		}
	}

	// public static void main(String[] args) throws ParseException {
	// Lexer lex = new Lexer(new CodeDialog());
	// Token t = null;
	// while ((t = lex.parse()) != Token.EOF) {
	// System.out.println(t.getText());
	// }
	// }
}
