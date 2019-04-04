public class LineBuffer extends PointBuffer {
    private Point centre;

    public LineBuffer(Point p1, Point p2){
        centre = new Point(((p1.getX()+p2.getX())/2),((p1.getY()+p2.getY())/2));
        double dist = Math.sqrt(
                Math.pow(p1.getX()-centre.getX(),2) +
                        Math.pow(p1.getY()-centre.getY(),2)
        );
        setRadius(20);
    }

}
