
public class Point {

    private double x,y;

    public Point(double xcoord, double ycoord){
        x = xcoord;
        y = ycoord;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point(){
        x = 0;
        y = 0;
    }

    public boolean isEqual(Point p) {
        return this.getX() == p.getX() && this.getY() == p.getY();
    }

    public void displayPoint()
    {
        System.out.println("(" + this.x + ", " + this.y + ")");
    }

    public double distance(Point pointB){
        return  Math.sqrt(Math.pow((this.x - pointB.x), 2) + Math.pow((this.y - pointB.y), 2));
    }

}