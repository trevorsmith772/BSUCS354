/**
 * Node for relational operators
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeRelop extends Node {

    private String relop;

    /* Constructor */
    public NodeRelop (String relop, int pos) {
        this.relop = relop;
        this.pos = pos;
    }

    public double op(double o1, double o2) throws EvalException {
        double ret;
        switch (relop){
            case "<":
                ret = ((o1 < o2) ? 1.0 : 0.0);  //Use 1 for true, 0 for false
                break;
            case ">":
                ret = ((o1 > o2) ? 1.0 : 0.0);
                break;
            case "<=":
                ret = ((o1 <= o2) ? 1.0 : 0.0);
                break;
            case ">=":
                ret = ((o1 >= o2) ? 1.0 : 0.0);
                break;
            case "==":
                ret = ((o1 == o2) ? 1.0 : 0.0);
                break;
            case "<>":
                ret = ((o1 != o2) ? 1.0 : 0.0);
                break;
            default:
                throw new EvalException(pos,"bogus relop: "+relop);
        }
        return ret;
    }
}
