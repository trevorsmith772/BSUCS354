/**
 * Node for fact => expression
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

    /**
     * Constructor
     * @param expr - expression node
     */
    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }

    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
