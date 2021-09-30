/**
 * Exception for eval method
 * Date: 2/21/2021
 * Author: Trevor Smith
 */
public class EvalException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int pos;
    private String msg;

    /**
     * Constructor
     * @param pos - index tracker
     * @param msg - message to be displayed in exception
     */
    public EvalException(int pos, String msg) {
	this.pos=pos;
	this.msg=msg;
    }

    /**
     * To string method for eval exception
     */
    public String toString() {
	return "eval error"
	    +", pos="+pos
	    +", "+msg;
    }

}
