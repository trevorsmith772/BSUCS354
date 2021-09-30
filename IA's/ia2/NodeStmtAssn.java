/**
 * Node for assignment statements
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeStmtAssn extends NodeStmt{
    
    private NodeExpr expr;
    private String str;

    /* Constructor */
    public NodeStmtAssn (NodeExpr expr, String str){
        this.expr = expr;
        this.str = str;
    }

    public double eval (Environment env) throws EvalException {
        return env.put(str, expr.eval(env));
    }
}
