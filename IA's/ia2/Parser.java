import java.util.*;

/**
 * This class is a recursive-descent parser,
 *	modeled after the programming language's grammar.
 *	It constructs and has-a Scanner for the program
 *	being parsed.
 * @author Trevor smith
 * Date: 3/18/2021
 */
public class Parser {

	private Scanner scanner;

	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	/**
	 * 
	 * @return scanner position
	 */
	private int pos() {
		return scanner.pos();
	}

	/**
	 * Parses for multiplication operator
	 * @return - new mulop node for given mulop
	 * @throws SyntaxException
	 */
	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	/**
	 * Parser for addition operator
	 * @return new addop node for given operator
	 * @throws SyntaxException
	 */
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	/**
	 * Parser for fact 
	 * @return new fact node for given type of fact
	 * @throws SyntaxException
	 */
	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		// Added for negative number and negation support
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact nodeFact = parseFact();
			return new NodeFactNeg(nodeFact);
		}

		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	/**
	 * Parser for term
	 * @return new term node
	 * @throws SyntaxException
	 */
	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	/**
	 * Parser for expression
	 * @return new expression node
	 * @throws SyntaxException
	 */
	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	/**
	 * Parser for boolean expressions
	 * @return new boolean expression node
	 * @throws SyntaxException
	 */
	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
        NodeRelop relop = parseRelop();
        NodeExpr expr2 = parseExpr();
        return new NodeBoolExpr(expr1, expr2, relop);
	}

	/**
	 * Parser for relop
	 * @return new relop node
	 * @throws SyntaxException
	 */
	private NodeRelop parseRelop() throws SyntaxException {
		if(curr().equals(new Token("<"))){
			match("<");
			return new NodeRelop("<", pos());
		}
		else if(curr().equals(new Token(">"))){
			match(">");
			return new NodeRelop(">", pos());
		}
		else if(curr().equals(new Token("<="))){
			match("<=");
			return new NodeRelop("<=", pos());
		}
		else if(curr().equals(new Token(">="))){
			match(">=");
			return new NodeRelop(">=", pos());
		}
		else if(curr().equals(new Token("=="))){
			match("==");
			return new NodeRelop("==", pos());
		}
		else {
			match("<>");
			return new NodeRelop("<>", pos());
		}
	}

	/**
	 * Parser for statements
	 * @return new statement node
	 * @throws SyntaxException
	 */
	private NodeStmt parseStmt() throws SyntaxException {
		if(curr().equals(new Token("id"))){
			Token id = curr();
			match("id");
			match("=");
			NodeExpr expr = parseExpr();
			return new NodeStmtAssn(expr, id.lex());
		}
		else if(curr().equals(new Token("rd"))){	//NodeStmtRd
			match("rd");
			Token id = curr();
			match("id");
			return new NodeStmtRd(id.lex());
		}
		else if(curr().equals(new Token("wr"))){	//NodeStmtWr
			match("wr");
			NodeExpr expr = parseExpr();
			return new NodeStmtWr(expr);
		}
		else if(curr().equals(new Token("if"))){	//NodeStmtIf
			match("if");
			NodeBoolExpr boolExpr = parseBoolExpr();
			match("then");
			NodeStmt ifStmt = parseStmt();
			if(curr().lex().equals("else")) {	//If,then,else
				match("else");
				NodeStmt elseStmt = parseStmt();
				return new NodeStmtIf(ifStmt, elseStmt, boolExpr);
			}
			else {		//If,then (no else)
				return new NodeStmtIf(ifStmt, null, boolExpr);
			}
		}
		else if(curr().equals(new Token("while"))){	//NodeStmtWhile
			match("while");
			NodeBoolExpr boolExpr = parseBoolExpr();
			match("do");
			NodeStmt whileStmt = parseStmt();
			return new NodeStmtWhile(boolExpr, whileStmt);
		}
		else {		//NodeStmtBegin
			match("begin");
			NodeBlock block = parseBlock();
			match("end");
			return new NodeStmtBegin(block);
		}
	}

	/**
	 * Parser for block as well as prog, since prog equates to block
	 * @return new NodeBlock
	 * @throws SyntaxException
	 */
	public NodeBlock parseBlock() throws SyntaxException {		NodeStmt stmt = parseStmt();
		NodeBlock block = null;
		if(curr().equals(new Token(";"))) {
			match(";");
			block = parseBlock();
		}
		return new NodeBlock(block, stmt);
	}

	/**
	 * Main parser method called from Interpreter
	 * @param program - the program to be run
	 * @return node from parsed block
	 * @throws SyntaxException
	 */
	public Node parse(String program) throws SyntaxException {
		scanner=new Scanner(program);
		scanner.next();
		return parseBlock();
	}
}
