import java.util.Scanner;

/**
 * Node for read statements (program input)
 * @author Trevor Smith
 * Date: 3/18/2021
 */
public class NodeStmtRd extends NodeStmt {
    
    private String str;
    private static Scanner scan;

    /* Constructor */
    public NodeStmtRd (String str){
        this.str = str;
    }

    public double eval (Environment env) throws EvalException {
        if(scan == null) {
            scan = new Scanner(System.in);
        }
        return env.put(str, scan.nextDouble());
    }
}
