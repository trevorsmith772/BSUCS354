/**
 * Node for fact => num
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class NodeFactNum extends NodeFact {

    private String num;

    /**
     * Constructor
     * @param num - number to be read
     */
    public NodeFactNum(String num) {
	this.num=num;
    }

    public double eval(Environment env) throws EvalException {
        return Double.parseDouble(num); //modified to read doubles
    }
}
