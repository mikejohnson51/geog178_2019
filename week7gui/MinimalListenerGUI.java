import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Use your classes if possible (you may need to add setRadius(double r)).


public class MinimalListenerGUI extends JPanel implements ActionListener {

	private PointBuffer pb;
	private Point circP;
	private JTextField rField; 
	
	public MinimalListenerGUI() {
		// Feel free to ignore, this just calls the constructor of JPanel to enable  DoubleBuffering to avoid flickering.
		super(true); 
		
		// Set the (preferred) size of the panel
		setPreferredSize(new Dimension(500,500));
	}
		
	// Strictly speaking we do not need the getter and setter methods 
	// if we only access the point and the buffer from inside the class
	public PointBuffer getPb() {
		return pb;
	}

	public void setPb(PointBuffer pb) {
		this.pb = pb;
	}
	
	public Point getCircP() {
		return circP;
	}

	public void setCircP(Point circP) {
		this.circP = circP;
	}

	/*
	 *  This is the new and interesting part. Our class implements the ActionListener interface 
	 *  and, hence, has to provide an implementation for public void actionPerformed(ActionEvent arg0).
	 *  Java will ensure that this method gets called if the button (see below) is clicked.
	 */
	public void actionPerformed(ActionEvent arg0) {
		/*
		 * Get the text from the text field, try to decode the text into an integer, set
		 * this integer as the new radius for the buffer, and, finally, repaint the GUI.
		 * You will get a NumberFormatException if the text field does not contain a number, we 
		 * will discuss exceptions next time.
		 */
		pb.setRadius(Integer.decode(rField.getText()));
		repaint();
	}
	
	// This method handles the animation of the circle.
	private void animation() {
		
		// A very simple animation - enjoy playing around with it!
		Point centre = getPb().getCentre();
		double r = getPb().getRadius();
		
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            double t = 2 * Math.PI * i / 360;
            getCircP().setX((double) Math.round(centre.getX() + r * Math.cos(t)));
            getCircP().setY((double) Math.round(centre.getY() + r * Math.sin(t)));
            centre = getPb().getCentre();
            r = getPb().getRadius();
			
			// Just to make sure you see a change we introduce 5ms breaks per cycle.
            // Ignore the try-catch block for now, we will discuss it next time.
            try{
    			Thread.sleep(5);
            }
            catch(InterruptedException e){System.out.println(e);}
			
			// Remove the repaint line and see what happens.
			repaint();
		}		
	}
	
	/*
	 * Java takes care of calling this method. For this reason we had to define MinimalDrawingGUI 
	 * as a subclass of JPanel (line 14). You do not need to call the method by hand. If, however, you
	 * change data in your model, e.g., the PointBuffer, you will have to call repaint().
	 * 
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paints all other stuff, e..g., background.
        
        /*
         * An example showing how to use your models/classes in the GUI.
         * Note that you have to 'cast' ,i.e., convert, doubles to integers.
         */
        g.drawOval(
        		(int) (pb.getCentre().getX()- pb.getRadius()),
        		(int) (pb.getCentre().getY()- pb.getRadius()), 
        		(int) pb.getRadius()*2, 
        		(int) pb.getRadius()*2);

        
        // Just for fun and to get used to the API
        g.setColor(Color.red);
        
        // The X and Y of the oval will be different in each iteration of the animation loop, see above.
        g.fillOval(
        		(int) getCircP().getX()-4,
        		(int) getCircP().getY()-4, 
        		8, 
        		8);
	}

	public static void main(String[] args) {
		
		MinimalListenerGUI gui = new MinimalListenerGUI();
		
		// Usually, you would do this somewhere else, this is just for simplification.
		gui.setPb(new PointBuffer(new Point(250,250), 100));
		gui.setCircP(new Point(0,0));
		
		// You always need a frame to place other components such as panels or buttons
		JFrame frame = new JFrame("A minimalistic GUI for drawing");
		
		// Exit on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a text field with an initial text.
		gui.rField =  new JTextField("Enter radius");
		
		// Create a button labeled "Go!"
		JButton goButton =  new JButton("Go!");
		
		/*
		 * The MinimalListenerGUI will listen for events from the goButton. Every time
		 * the button gets clicked it will call the actionPerformed method of gui; see above.
		 */
		goButton.addActionListener(gui);

		// Add an instance of the MinimalDrawingGUI to the frame.
		frame.add(gui);
		
		// Add the text field and the button to the JPanel (our GUI).
		gui.add(gui.rField);
		gui.add(goButton);
		
		// Set the size, arrange components, and display the frame.
		frame.pack();
		frame.setVisible(true);
		
		// Run the animation.
		gui.animation();
	}

}
