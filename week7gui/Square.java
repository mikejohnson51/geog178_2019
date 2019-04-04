import java.util.ArrayList;

public class Square implements Geometry {
    private double a;
    private Point UL, LR;
    private Point startPoint;


    public Square(Point start, double a) {
        this.startPoint = start;
        this.a = a;
    }

    public double getX() { return startPoint.getX(); }
    public double getY() { return startPoint.getY(); }
    public double getWidth() { return this.a; }

    public double getArea() {
        return Math.pow(a,2);
    }

    public double getLength() { return 4*a; }

    public ArrayList<Point> getBB() {
        ArrayList<Point> bb = new ArrayList<Point>();

        UL = new Point(this.startPoint.getX()-5,this.startPoint.getY()-5);
        LR = new Point( this.startPoint.getX() + this.a+5, this.startPoint.getY() + this.a+5);

        bb.add(UL);
        bb.add(LR);
        return bb;
    }
}
