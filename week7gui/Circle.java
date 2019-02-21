import java.util.ArrayList;

public class Circle implements Geometry {
    private double r;
    private Point center;
    private Point UL, LR;

    public Circle(Point center, double radius) {
        this.r = radius;
        this.center = center;
    }

    public Circle() {
        this.r = 0;
    }

    public Point getCenter() { return this.center; }
    public double getRadius() { return this.r; }

    public double getArea() {
        return Math.PI*Math.pow(r,2);
    }

    public double getLength() { return 2*Math.PI*r; }

    public ArrayList<Point> getBB() {
        ArrayList<Point> bb = new ArrayList<Point>();

        UL = new Point( this.center.getX() - this.r, this.center.getY() - this.r);
        LR = new Point( this.center.getX() + this.r, this.center.getY() + this.r);

        bb.add(UL);
        bb.add(LR);
        return bb;
    }

}
