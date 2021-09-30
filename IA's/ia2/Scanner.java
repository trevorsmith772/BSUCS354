// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

public class Scanner {

	private String program; // source program being interpreted
	private int pos; // index of next char in program
	private Token token; // last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace = new HashSet<String>();
	private Set<String> digits = new HashSet<String>();
	private Set<String> letters = new HashSet<String>();
	private Set<String> legits = new HashSet<String>();
	private Set<String> keywords = new HashSet<String>();
	private Set<String> operators = new HashSet<String>();

	// Set for comments
	private Set<String> comments = new HashSet<String>();

	// initializers for previous sets

	private void fill(Set<String> s, char lo, char hi) {
		for (char c = lo; c <= hi; c++)
			s.add(c + "");
	}

	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	private void initDigits(Set<String> s) {
		fill(s, '0', '9');
  		s.add(".");	//added to support doubles
	}

	private void initLetters(Set<String> s) {
		fill(s, 'A', 'Z');
		fill(s, 'a', 'z');
	}

	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
		//Relops
		s.add("<");
		s.add(">");
		s.add("<=");
		s.add(">=");
		s.add("==");
		s.add("<>");
	}

	private void initKeywords(Set<String> s) {
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
	}

	/**
	 * method used to initialze comments set
	 * with different comment indicators
	 * @param s - set of strings containing comment
	 * 			indicators
	 */
	private void initComments(Set<String> s) {
		s.add("_/"); // inline comment character (hockey stick)
		s.add("_/."); // start of block comment (hockey stick with puck)
		s.add("\\_."); // end of block comment (hockey stick with puck)
	}

	// constructor:
	// - squirrel-away source program
	// - initialize sets
	public Scanner(String program) {
		this.program = program;
		pos = 0;
		token = null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
		initComments(comments);
	}

	// handy string-processing methods

	public boolean done() {
		return pos >= program.length();
	}

	private void many(Set<String> s) {
		while (!done() && s.contains(program.charAt(pos) + ""))
			pos++;
	}

	// private void past(char c) {
	// 	while (!done() && c != program.charAt(pos))
	// 		pos++;
	// 	if (!done() && c == program.charAt(pos))
	// 		pos++;
	// }

	// scan various kinds of lexeme

	private void nextNumber() {
		int old = pos;
		many(digits);
		token = new Token("num", program.substring(old, pos));
	}

	private void nextKwId() {
		int old = pos;
		many(letters);
		many(legits);
		String lexeme = program.substring(old, pos);
		token = new Token((keywords.contains(lexeme) ? lexeme : "id"), lexeme);
	}

	private void nextOp() {
		int old = pos;
		pos = old + 2;
		if (!done()) {
			String lexeme = program.substring(old, pos);
			if (operators.contains(lexeme)) {
				token = new Token(lexeme); // two-char operator
				return;
			}
		}
		pos = old + 1;
		String lexeme = program.substring(old, pos);
		token = new Token(lexeme); // one-char operator
	}

	private void nextComment() {
		int old = pos;
		pos = old + 2;
		many(comments);
		String lex = program.substring(old, pos);
		if (lex.matches("_/") && !(program.substring(pos, pos + 1).equals("."))) { // line comment
			while (!done()) {
				lex = program.substring(old, pos);
				pos++;
			}
			lex = program.substring(old, pos);
			token = new Token("EOF");
		}
		else if(lex.matches("_/") && program.substring(pos, pos + 1).matches(".")){ //block comment
			while(!lex.endsWith("\\_.")){
				pos++;
				lex = program.substring(old, pos);
			}
			lex = program.substring(old, pos);
			if (done()) {
				token = new Token("EOF");
			} else {
				next();
			}
		}
	}

	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
	public boolean next() {
		many(whitespace);
		if (done()) {
			token = new Token("EOF");
			return false;
		}
		String c = program.charAt(pos) + "";
		if (comments.contains(c+"/") || comments.contains(c+"/."))
			nextComment();
		else if (digits.contains(c))
			nextNumber();
		else if (letters.contains(c))
			nextKwId();
		else if (operators.contains(c))
			nextOp();
		else {
			System.err.println("illegal character at position " + pos);
			pos++;
			return next();
		}
		return true;
	}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos, t, curr());
		next();
	}

	public Token curr() throws SyntaxException {
		if (token == null)
			throw new SyntaxException(pos, new Token("ANY"), new Token("EMPTY"));
		return token;
	}

	public int pos() {
		return pos;
	}

	// for unit testing
	// public static void main(String[] args) {
	// 	try {
	// 		Scanner scanner = new Scanner(args[0]);
	// 		while (scanner.next())
	// 			System.out.println(scanner.curr());
	// 	} catch (SyntaxException e) {
	// 		System.err.println(e);
	// 	}
	// }
}
