public class PointBuffer {

	private Point centre;
	private double radius; // This is a circle
	
	public PointBuffer(Point p, double r){
		centre = p;
		radius = r;
	}
	
	public boolean isInside(Point p){
		// What about calling the distance method? :-)
		if(radius > (Math.sqrt(
				Math.pow((centre.getX()-p.getX()), 2)+ 
				Math.pow((centre.getY()-p.getY()), 2))))
			return true;
		else
			return false;
	}
	
	public Point getCentre(){
		return centre;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public void setRadius(double r){
		radius = r;
	}
	
}
