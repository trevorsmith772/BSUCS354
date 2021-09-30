/**
 * Node for a statement
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class NodeStmt extends Node {

    private NodeAssn assn;

    /**
     * Constructor
     * @param assn - assignment node
     */
    public NodeStmt(NodeAssn assn) {
	this.assn=assn;
    }

    public double eval(Environment env) throws EvalException {
	return assn.eval(env);
    }

}
