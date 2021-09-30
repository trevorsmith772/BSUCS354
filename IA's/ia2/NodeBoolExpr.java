/**
 * Node for boolean expressions
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeBoolExpr extends Node{
    
    private NodeExpr expr1;
    private NodeExpr expr2;
    private NodeRelop relop;

    /* Constructor */
    public NodeBoolExpr (NodeExpr expr1, NodeExpr expr2, NodeRelop relop){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.relop = relop;
    }

    public double eval(Environment env) throws EvalException {
	    return relop.op(expr1.eval(env), expr2.eval(env));
    }
}
