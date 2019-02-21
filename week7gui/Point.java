import java.util.ArrayList;

public class Point implements Geometry {
	
	private double x, y;
	private Point UL, LR;


	public Point(double x, double y){
		this.x = x;
		this.y= y;
	}
	
	public Point(){
		this(0,0);
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
	
	public String toString(){
		return "("+x+","+y+")";
	}


	public double getLength() { return 0; }
	public double getArea() { return 0; }
	public ArrayList<Point> getBB() {
		ArrayList<Point> bb = new ArrayList<Point>();

		UL = new Point( this.getX() - 2, this.getY() + 2);
		LR = new Point( this.getX() + 2, this.getY() - 2);

		bb.add(UL);
		bb.add(LR);
		return bb;
	}

}
