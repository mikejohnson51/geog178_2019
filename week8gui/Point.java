
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


}