/**
 * Node for negative digits, extends NodeFact 
 * Author: Trevor Smith 
 * Date: 2/25/20
 */
public class NodeFactNeg extends NodeFact {

    private NodeFact negative;

    /**
     * Constructor
     * @param nodeFact fact node to be evaluated
     */
    public NodeFactNeg(NodeFact nodeFact) {
        this.negative = nodeFact;
    }

    public double eval(Environment env) throws EvalException {

        return -negative.eval(env);
    }
}
