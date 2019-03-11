import java.io.Serializable;
import java.util.ArrayList;

public class SaveFile implements Serializable {

    private Line pp;
    private Polyline pl;
    private Node nn;
    private ArrayList<PointBuffer> pb;
    private ArrayList<PointBuffer> pb2;
    private ArrayList<PointBuffer> streets;

    // new parameterized constructor to save array list of Point Buffer
    //public SaveFile (ArrayList<PointBuffer> pb, ArrayList<PointBuffer> pb2) {
    //	this.pb = pb;
    //	this.pb2 = pb2;
    //}

    public SaveFile (ArrayList<PointBuffer> streets, ArrayList<PointBuffer> pb) {
        this.streets = streets;
        this.pb = pb;
    }

    public SaveFile (Line pp, Polyline pl) {
        this.pp = pp;
        this.pl = pl;
    }

    public SaveFile (Line pp, Nodes nn) {

    }

    public SaveFile (ArrayList<PointBuffer> streets) {
        this.streets = streets;
    }

    public Line getPp() {
        return pp;
    }

    public ArrayList<PointBuffer> getStreets() {
        return streets;
    }

    public void setPp(Line pp) {
        this.pp = pp;
    }

    public Polyline getPl() {
        return pl;
    }

    public void setPl(Polyline pl) {
        this.pl = pl;
    }

}




//}
