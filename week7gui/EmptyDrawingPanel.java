import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*; // for stuff like linkedlist used in full bounding box


import javax.swing.*;

// A over-simplified GUI for drawing.
public class EmptyDrawingPanel extends JPanel implements ActionListener {

	// A very simple example. In your case your points, polylines, polygons, and buffers go here.
	// Do not use the AWT Point, Polyline, and Polygon classes, they are only for painting. Use your own classes.
	private ArrayList<Point> somePoints;

	private JButton goButton =  new JButton("Polygon It!"); // defining buttons here for use throughout
	private JButton runButton =  new JButton("Bound!");
	private JButton run2Button =  new JButton("Mega-bound!");

	private boolean checkRun = false; // whether to run bounding boxes
	private boolean checkRun2 = false;


	public EmptyDrawingPanel() {
		// Feel free to ignore, this just calls the constructor of JPanel to enable  DoubleBuffering to avoid flickering.
		super(true); 
		
		somePoints = new ArrayList<Point>();


		// Set the (preferred) size of the panel
		setPreferredSize(new Dimension(1200,500));
	}

	/*
	 * Java takes care of calling this method. For this reason we had to define EmptyDrawingPanel 
	 * as a subclass of JPanel above. You do not need to call the method by hand. If, however, you
	 * change data in your model, e.g., the Point, you will have to call repaint() and repaint will call
	 * paintComponent and give it the graphic object g.
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);    // Paints all other stuff, e.g., background.


		for (int i = 0; i < somePoints.size() - 1; i++) {
			g.setColor(Color.black);
			g.drawLine(
					(int) (somePoints.get(i)).getX(),
					(int) (somePoints.get(i)).getY(),
					(int) (somePoints.get(i + 1)).getX(),
					(int) (somePoints.get(i + 1)).getY()
			);
			g.setColor(Color.RED);
			g.fillOval((int) (somePoints.get(i)).getX() - 4, (int) (somePoints.get(i)).getY() - 4, 10, 10);
			g.fillOval((int) (somePoints.get(i + 1)).getX() - 4, (int) (somePoints.get(i + 1)).getY() - 4, 10, 10);
		}
		// circle to be drawn
		Circle c = new Circle(new Point(400,400),60);

		// drawing circle shape
		g.setColor(Color.orange);

		//g.setColor(Color.getHSBColor(26,81,63)); // using hue saturation and brightness
		g.drawOval(
				(int) (c.getCenter().getX() - c.getRadius()),
				(int) (c.getCenter().getY() - c.getRadius()),
				(int) c.getRadius() * 2,
				(int) c.getRadius() * 2);

		// setting color for rect
		g.setColor(Color.green);

		// new rect to draw
		Rectangle rect = new Rectangle(new Point(600,200),90,120);

		g.drawRect(
				(int) rect.getX(),
				(int) rect.getY(),
				(int) rect.getWidth(),
				(int) rect.getHeight());

		// for square
		g.setColor(Color.blue);


		Square sq1 = new Square(new Point(900,200),78);


		g.drawRect(
				(int) sq1.getX(),
				(int) sq1.getY(),
				(int) sq1.getWidth(),
				(int) sq1.getWidth());

		// if bound it button is pressed, individual bounding boxes
		if (checkRun) {
			// Circle bb
			ArrayList<Point> c_bb;
			c_bb = c.getBB();
			g.setColor(Color.gray);

			g.drawRect(
					(int) c_bb.get(0).getX(),
					(int) c_bb.get(0).getY(),
					Math.abs((int) c_bb.get(1).getX() - (int) c_bb.get(0).getX()),
					Math.abs((int) c_bb.get(1).getY() - (int) c_bb.get(0).getY()));

			// Rectangle bb
			ArrayList<Point> r_bb;
			r_bb = rect.getBB();

			g.drawRect(
					(int) r_bb.get(0).getX(),
					(int) r_bb.get(0).getY(),
					Math.abs((int) r_bb.get(1).getX() - (int) r_bb.get(0).getX()),
					Math.abs((int) r_bb.get(1).getY() - (int) r_bb.get(0).getY()));

			// Square bb
			ArrayList<Point> sq_bb;
			sq_bb = sq1.getBB();

			g.drawRect(
					(int) sq_bb.get(0).getX(),
					(int) sq_bb.get(0).getY(),
					Math.abs((int) sq_bb.get(1).getX() - (int) sq_bb.get(0).getX()),
					Math.abs((int) sq_bb.get(1).getY() - (int) sq_bb.get(0).getY()));

			checkRun = false;
		}

		// if mega-bound it button is pressed, overall bounding box

		if (checkRun2) {
			ArrayList<Point> bbPoints = new ArrayList<Point>();
			bbPoints.addAll(c.getBB());
			bbPoints.addAll(rect.getBB());
			bbPoints.addAll(sq1.getBB());
			bbPoints.addAll(somePoints); // one list

			List<Double> listx = new LinkedList<Double>(); // to linkedlist to use collections
			List<Double> listy = new LinkedList<Double>();


			for (int i = 0; i < bbPoints.size(); i++) { // add to lists
					listx.add(bbPoints.get(i).getX());
					listy.add(bbPoints.get(i).getY());

			}

			double minx = Collections.min(listx); // determine bounds of box
			double maxx = Collections.max(listx);
			double miny = Collections.min(listy);
			double maxy = Collections.max(listy);

			g.setColor(Color.getHSBColor(34,420,56)); // using hue saturation brightness

			if (bbPoints != null) {

				g.drawRect(
						(int) minx, (int) miny, (int) maxx - (int) minx, (int) maxy- (int) miny);

				checkRun = false;
			}
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		/*
		 * Get the text from the text field, try to decode the text into an integer, set
		 * this integer as the new radius for the buffer, and, finally, repaint the GUI.
		 * You will get a NumberFormatException if the text field does not contain a number, we
		 * will discuss exceptions next time.
		 */
		if(arg0.getSource() == goButton) {
			somePoints.add(somePoints.get(0));
			repaint();
		}
		if(arg0.getSource()== runButton) {
			checkRun = true;
			repaint();

		}
		if(arg0.getSource() == run2Button) {
			checkRun2 = true;
			repaint();
		}
	}



	public static void main(String[] args) throws InterruptedException {
		
		EmptyDrawingPanel aPanel = new EmptyDrawingPanel();
		
		aPanel.somePoints.add(new Point(100,100));
		aPanel.somePoints.add(new Point(200,200));
		aPanel.somePoints.add(new Point(250,300));
			
		// You always need a frame to place other components such as panels or buttons
		JFrame frame = new JFrame("A (sort-of) full panel");




		/*
		 * The MinimalListenerGUI will listen for events from the goButton. Every time
		 * the button gets clicked it will call the actionPerformed method of gui; see above.
		 */
		aPanel.goButton.addActionListener(aPanel);
		aPanel.runButton.addActionListener(aPanel);
		aPanel.run2Button.addActionListener(aPanel);


		// Add the text field and the button to the JPanel (our GUI).
		aPanel.add(aPanel.goButton);
		aPanel.add(aPanel.runButton);
		aPanel.add(aPanel.run2Button);

		// Add an instance of the MinimalDrawingGUI to the frame.
		frame.add(aPanel);



		// Set the size, arrange components, and display the frame.
		frame.pack();
		frame.setVisible(true);
		
		// exit on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

