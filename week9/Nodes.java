import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public class Nodes {

    //Attributes
    private ArrayList<Node> nodes;

    //Constructors
    public Nodes() { nodes = new ArrayList<Node>(); }

    //Delegates
    public int size() { return nodes.size(); }
    public Node get(int index) { return nodes.get(index); }
    public boolean add(Node e) {
        if(e.getP().getX() != Double.MAX_VALUE && e.getP().getY() != Double.MAX_VALUE) { return nodes.add(e); } else { return true; }
    }

    public Node remove(int index) { return nodes.remove(index); }

    //Methods
    public Nodes removeDuplicates() {

        for (int i = 0; i < this.size(); i++) {
            for (int j = i + 1; j < this.size(); j++) {
                if (this.get(i).getP().isEqual(this.get(j).getP())) {
                    this.remove(j);
                }
            }
        }

        return (this);
    }
}
