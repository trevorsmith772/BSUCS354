/**
 * Node for multiplication/division operators
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class NodeMulop extends Node {

    private String mulop;

	/**
	 * Constructor
	 * @param pos - index tracker
	 * @param mulop - multiplication operator
	 */
    public NodeMulop(int pos, String mulop) {
	this.pos=pos;
	this.mulop=mulop;
    }

	/**
	 * op - performs given operation
	 * @param o1 - first operand
	 * @param o2 - second operand
	 * @return the result of the multiplication or division operation
	 * @throws EvalException
	 */
    public double op(double o1, double o2) throws EvalException {
	if (mulop.equals("*"))
	    return o1*o2;
	if (mulop.equals("/"))
	    return o1/o2;
	throw new EvalException(pos,"bogus mulop: "+mulop);
    }

}
