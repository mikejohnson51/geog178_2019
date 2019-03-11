import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Lines {

    //Attributes
    private ArrayList<Line> lines;

    //Constructors
    public Lines () { lines = new ArrayList<Line>();}

    //Delegates
    public Line get(int index) { return lines.get(index); }
    public boolean add(Line e) { return lines.add(e); }
    public int size() { return lines.size(); }

    //Methods
    public Nodes buildNodes() {

        Nodes nodes = new Nodes();

        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                Point p = this.get(i).intersect(this.get(j));
                Node n = new Node(p, i, j);
                nodes.add(n);
                nodes.add(new Node(this.get(i).getE(), i, i));
                nodes.add(new Node(this.get(i).getS(), i, i));
            }
        }

        nodes.removeDuplicates();
        return(nodes);
    }

    public Lines buildGraph() {

        Nodes nodes = this.buildNodes();
        Lines la = new Lines();

        for (int i = 0; i < this.size(); i++) {

            Nodes tmpNodes = new Nodes();
            Polyline oNodes = new Polyline(); //PolyLine??
            ArrayList<Double> dist = new ArrayList<Double>();

            for (int k = 0; k < nodes.size(); k++)
            {
                if(nodes.get(k).getLine1() == i | nodes.get(k).getLine2() == i	) {
                    tmpNodes.add(nodes.get(k));
                }
            }

            oNodes.add(this.get(i).getS());

            for (int j = 0; j < tmpNodes.size(); j++)
            {
                dist.add(oNodes.get(0).distance(tmpNodes.get(j).getP()));
            }

            for (int t = 0; t < dist.size(); t++)
            {
                int index = dist.indexOf(Collections.min(dist));
                oNodes.add(tmpNodes.get(index).getP());
                dist.set(index, Double.MAX_VALUE);
            }

            oNodes.add(this.get(i).getE());

            for (int b = 0; b < oNodes.size()- 1; b++) {
                la.add(new Line(oNodes.get(b), oNodes.get(b+1)));
            }
        }

        return(la);

    }
}
