/**
 * Node class for an expression
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

	/**
	 * Constructor
	 * @param term - term node
	 * @param addop - addition operator node
	 * @param expr - expression node
	 */
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
	this.term=term;
	this.addop=addop;
	this.expr=expr;
    }

	/**
	 * append - recursive method for creating expression
	 * @param expr - expression node
	 */
    public void append(NodeExpr expr) {
	if (this.expr==null) {
	    this.addop=expr.addop;
	    this.expr=expr;
	    expr.addop=null;
	} else
	    this.expr.append(expr);
    }

    public double eval(Environment env) throws EvalException {
	return expr==null
	    ? term.eval(env)
	    : addop.op(expr.eval(env),term.eval(env));
    }

}
