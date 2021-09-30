/**
 * Class for block node
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeBlock extends NodeStmt {
    
    private NodeBlock block;
    private NodeStmt stmt;

    /* Constructor */
    public NodeBlock(NodeBlock block, NodeStmt stmt){
        this.block = block;
        this.stmt = stmt;
    }

    public double eval (Environment env) throws EvalException {
        double eval = stmt.eval(env);
        if(block != null) {
            return block.eval(env);
        }
        else {
            return eval;
        }
    }
}
