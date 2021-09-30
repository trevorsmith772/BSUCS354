/**
 * Node for if statements
 * @author Trevor Smith
 * Date: 3/18/21
 */
public class NodeStmtIf extends NodeStmt {
    
    private NodeStmt stmt1;
    private NodeStmt stmt2;
    private NodeBoolExpr boolExpr;

    /* Constructor */
    public NodeStmtIf (NodeStmt stmt1, NodeStmt stmt2, NodeBoolExpr boolExpr) {
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.boolExpr = boolExpr;
    }

    public double eval (Environment env) throws EvalException {
        if(boolExpr.eval(env) == 1){
            return stmt1.eval(env);
        }
        if(stmt2 == null){
            return 0;
        }
        else{
            return stmt2.eval(env);
        }
    }
}
