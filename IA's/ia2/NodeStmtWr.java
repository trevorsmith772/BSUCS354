/**
 * Node for write statements (print to console)
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeStmtWr extends NodeStmt{

    private NodeExpr expr;
    
    /* Constructor */
    public NodeStmtWr (NodeExpr expr){
        this.expr = expr;
    }

    public double eval (Environment env) throws EvalException {
        double output = expr.eval(env);
        System.out.println(expr.eval(env));
        return output;
    }
}
