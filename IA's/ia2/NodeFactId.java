/**
 * Node for fact => ID
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class NodeFactId extends NodeFact {

    private String id;

    /**
     * Constructor
     * @param pos - index tracker
     * @param id - identifier
     */
    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }

    public double eval(Environment env) throws EvalException {
	return env.get(pos,id);
    }

}
