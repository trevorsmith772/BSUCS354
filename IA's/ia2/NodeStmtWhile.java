/**
 * Node for while statements
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeStmtWhile extends NodeStmt {
    
    private NodeBoolExpr boolExpr;
    private NodeStmt stmt;

    /* Constructor */
    public NodeStmtWhile (NodeBoolExpr boolExpr, NodeStmt stmt){
        this.boolExpr = boolExpr;
        this.stmt = stmt;
    }

    public double eval (Environment env) throws EvalException {
        while(boolExpr.eval(env) == 1){
            stmt.eval(env);
        }
        return 0;
    }
}
