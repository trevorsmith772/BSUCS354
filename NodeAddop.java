import java.text.DecimalFormat;

/**
 * Node for addition operators
 * 
 * Date: 2/21/2021 
 * Author: Trevor Smith
 */
public class NodeAddop extends Node {

	private String addop;

	/**
	 * NodeAddop Constructor
	 * 
	 * @param pos   - index tracker
	 * @param addop - addition operator
	 */
	public NodeAddop(int pos, String addop) {
		this.pos = pos;
		this.addop = addop;
	}

	/**
	 * op performs the add or subtract operation between two parameters passed in.
	 * 
	 * @param o1 - double to be added/subtracted
	 * @param o2 - double to be added/subtracted
	 * @return result of add or subtract operation
	 * @throws EvalException
	 */
	public double op(double o1, double o2) throws EvalException {
		DecimalFormat dFormat = new DecimalFormat("#0.000000");
		if (addop.equals("+"))
			return o1 + o2;	// Adds together if addop is +
		if (addop.equals("-"))
			return Double.parseDouble(dFormat.format(o1 - o2));	//subtracts if addop is -
		throw new EvalException(pos, "bogus addop: " + addop);
	}

}
