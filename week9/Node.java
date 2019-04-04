import java.util.ArrayList;

public class Node {

    //Attributes
    private Point p;
    private int line1;
    private int line2;

    //Constructors
    public Node(Point p, int l1, int l2) {
        this.p = p;
        this.line1 = l1;
        this.line2 = l2;
    }

    //Getters and Setters

    public Point getP()             { return p; }
    public void setP(Point p)       { this.p = p; }
    public int getLine1()           { return line1; }
    public void setLine1(int line1) { this.line1 = line1; }
    public int getLine2()           { return line2; }
    public void setLine2(int line2) { this.line2 = line2; }

    //Methods??

}
