import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


// A over-simplified GUI for drawing.
public class MinimalDrawingGUI extends JPanel {

	private PointBuffer pb;
	
	public MinimalDrawingGUI() {
		// Feel free to ignore, this just calls the constructor of JPanel to enable  DoubleBuffering to avoid flickering.
		super(true); 
		
		// Set the (preferred) size of the panel
		setPreferredSize(new Dimension(500,500));
	}
	public PointBuffer getPb() {
		return pb;
	}

	public void setPb(PointBuffer pb) {
		this.pb = pb;
	}

	/*
	 * Java takes care of calling this method. For this reason we had to define MinimalDrawingGUI 
	 * as a subclass of JPanel (line 14). You do not need to call the method by hand. If, however, you
	 * change data in your model, e.g., the PointBuffer, you will have to call repaint().
	 * 
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paints all other stuff, e..g., background.
        
        // Cast to a Graphics2D object to support affine transformations
        Graphics2D g2d = (Graphics2D) g;
        //        g2d.shear(0.53 0.15);     
        //      g2d.rotate(0.25);
        //       g2d.scale(2.0, -1.5);
        
        // A simple line example; keep in mind we use display coordinates!
        g2d.drawLine(100, 100, 300, 400);
        
        // Just for fun and to get used to the API
        g2d.setColor(Color.red);
        
        /*
         * An example showing how to use your models/classes in the GUI.
         * Note that you have to 'cast' ,i.e., convert, doubles to integers.
         */
        g2d.drawOval(
        		(int) pb.getCentre().getX(),
        		(int) pb.getCentre().getY(), 
        		(int) pb.getRadius(), 
        		(int) pb.getRadius());

	}

	public static void main(String[] args) throws InterruptedException {
		
		MinimalDrawingGUI gui = new MinimalDrawingGUI();
		
		// Usually, you would do this somewhere else, this is just for simplification.
		gui.setPb(new PointBuffer(new Point(100,100), 20));
		
		// You always need a frame to place other components such as panels or buttons
		JFrame frame = new JFrame("A minimalistic GUI for drawing");

		// Add an instance of the MinimalDrawingGUI to the frame.
		frame.add(gui);
		
		// Set the size, arrange components, and display the frame.
		frame.pack();
		frame.setVisible(true);
		
		// exit on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// A very simple animation - enjoy playing around with it!
		Point centre = gui.getPb().getCentre();
		for(int i=0;i<450;i++){
			centre.setX(i);
			centre.setY(i);
			
			// Just to make sure you see a change we introduce 10ms breaks per cycle.
			Thread.sleep(20);
			
			// Remove the repaint line and see what happens.
			gui.repaint();
		}
	}
}
