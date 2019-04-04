
import java.util.ArrayList;


public class Polyline {

    private ArrayList<Point> pointlist;
    private double length =0;

    public Polyline() {
        pointlist = new ArrayList<Point>();
    }

    public boolean add(Point e) {
        //computeLength(); why would it make more sense to have a getLength and a computeLength method?
        return pointlist.add(e);
    }

    public Point get(int index) {
        return pointlist.get(index);
    }

    public int size() {
        return pointlist.size();
    }

    public double getLength() {
        length = 0; // Can you explain why?
        for(int i=0; i<size()-1;i++){
            length += Math.sqrt(Math.pow((get(i).getX()-get(i+1).getX()), 2)+ Math.pow((get(i).getY()-get(i+1).getY()), 2));
        }
        return length;
    }

}
