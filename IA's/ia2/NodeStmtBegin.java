/**
 * Node for begin statements
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeStmtBegin extends NodeStmt {

    private NodeBlock block;

    /* Constructor */
    public NodeStmtBegin (NodeBlock block) {
        this.block = block;
    }

    public double eval (Environment env) throws EvalException {
        return block.eval(env);
    }
}