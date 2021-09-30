/**
 * Node for program
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeProg extends Node {
    
    private NodeBlock block;

    /* Constructor */
    public NodeProg(NodeBlock block){
        this.block = block;
    }

    public double eval (Environment env) throws EvalException {
        return block.eval(env);
    }
}
