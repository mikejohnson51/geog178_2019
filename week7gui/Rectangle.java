import java.util.ArrayList;

public class Rectangle implements Geometry {
    private double a,b;
    private Point UL, LR;
    private Point startPoint;

    public Rectangle(Point start, double a, double b) {
        this.startPoint = start;
        this.a = a;
        this.b = b;
    }

    public double getX() { return startPoint.getX(); }
    public double getY() { return startPoint.getY(); }
    public double getWidth() { return this.a; }
    public double getHeight() { return this.b; }

    public double getArea() {
        return a*b;
    }

    public double getLength() { return (2*a)+(2*b); }

    public ArrayList<Point> getBB() {
        ArrayList<Point> bb = new ArrayList<Point>();

        UL = new Point(this.startPoint.getX()-5,this.startPoint.getY()-5);
        LR = new Point( this.startPoint.getX() + this.a+5, this.startPoint.getY() + this.b+5);

        bb.add(UL);
        bb.add(LR);
        return bb;
    }
}
