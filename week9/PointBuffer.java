import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class PointBuffer implements Serializable {

    private Point centre;
    private double radius; // This is a circle

    // Constructors
    public PointBuffer() {
        centre = new Point(0,0);
        radius = 1;
    }

    public PointBuffer(Point p) {
        centre = p;
        radius = 20;

    }

    public PointBuffer(Point p, double r){
        centre = p;
        radius = r;
    }

    // getters and setters
    public Point getCentre(){
        return centre;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double r){
        radius = r;
    }

    // methods
    public boolean isInside(Point p){
        // What about calling the distance method? :-)
        if(radius > (Math.sqrt(
                Math.pow((centre.getX()-p.getX()), 2)+
                        Math.pow((centre.getY()-p.getY()), 2))))
            return true;
        else
            return false;
    }


    //public void draw (Graphics g) {
    //	g.fillOval((int) (this.getCentre() - 5), (int) (this.getRadius() - 5), 10, 10);
    //}

    //public boolean isEqual(Point p) {
    // return this.getX() == p.getX() && this.getY() == p.getY();
    //}
}//}