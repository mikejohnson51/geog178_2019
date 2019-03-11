import java.awt.Graphics;
import java.util.ArrayList;

public class Line {

    //Attributes
    private Point s, e;

    //Constructor
    public Line(Point s, Point e) {
        this.e = e;
        this.s = s;
    }

    //Getters and Setters
    public Point getS() { return s; }
    public void  setS(Point s) { this.s = s; }
    public Point getE() { return e; }
    public void  setE(Point e) { this.e = e; }

    //Methods
    public Point intersect(Line l) {

        // Line 'this' represented as a1x + b1y = c1
        double a1 = this.getE().getY() - this.getS().getY(); //rise
        double b1 = this.getS().getX() - this.getE().getX(); //run
        double c1 = a1 * this.getS().getX() + b1 * this.getS().getY(); //

        // Line 'l' represented as a2x + b2y = c2
        double a2 = l.getE().getY() - l.getS().getY();
        double b2 = l.getS().getX() - l.getE().getX();
        double c2 = a2 * l.getS().getX() + b2 * l.getS().getY();

        double delta = a1 * b2 - a2 * b1;

        if (delta == 0) // if 0 then lines are parrallel (eg, no intersection)
        {
            return new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        else
        {
            double x = (b2*c1 - b1*c2)/delta;
            double y = (a1*c2 - a2*c1)/delta;

            Point tmpP = new Point(x, y);
            if(this.isInside(tmpP)) {
                return tmpP;
            } else {
                return new Point(Double.MAX_VALUE, Double.MAX_VALUE);
            }
        }
    }

    public void draw(Graphics g) {
        g.drawLine((int) this.getE().getX(), (int) this.getE().getY(), (int) this.getS().getX(), (int) this.getS().getY());
    }

    public boolean isInside(Point p) {

        double minX = Math.min(this.getE().getX(), this.getS().getX());
        double maxX = Math.max(this.getE().getX(), this.getS().getX());

        double minY = Math.min(this.getE().getY(), this.getS().getY());
        double maxY = Math.max(this.getE().getY(), this.getS().getY());

        return p.getX() >= minX && p.getX() <=maxX && p.getY() <= maxY && p.getY() >= minY;

    }
}