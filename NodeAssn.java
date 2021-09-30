/**
 * NodeAssn is a node class for an assignment 
 * Date: 2/21/2021 
 * Author: Trevor Smith
 */
public class NodeAssn extends Node {

    private String id;
    private NodeExpr expr;

    /**
     * Constructor
     * @param id - identifier
     * @param expr - expression
     */
    public NodeAssn(String id, NodeExpr expr) {
        this.id = id;
        this.expr = expr;
    }

    public double eval(Environment env) throws EvalException {
        return env.put(id, expr.eval(env));
    }

}
