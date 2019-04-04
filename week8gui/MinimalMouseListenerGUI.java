
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MinimalMouseListenerGUI extends JPanel implements MouseListener, MouseMotionListener {

    // These variables will store the mouse coordinates
    private int x=0,y=0;
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Color> colors = new ArrayList<Color>();

    public MinimalMouseListenerGUI() {
        // Feel free to ignore, this just calls the constructor of JPanel to enable  DoubleBuffering to avoid flickering.
        super(true);

        // Set the (preferred) size of the panel
        setPreferredSize(new Dimension(500,500));


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.setColor(Color.black);
        //Draw text showing the current mouse position.
        g2d.drawString("Mouse coordinates: "+x+", "+y, 15, 30);


        for (int i = 0; i < points.size(); i++) {
            //g2d.setColor(new Color(r,g1,b));
            int r = (int) (Math.random()*255)+1;
            int g1 = (int) (Math.random()*255)+1;
            int b = (int) (Math.random()*255)+1;
            g2d.setColor(new Color(r,g1,b));


        }

        Iterator<Point> iter = points.iterator();



        while(iter.hasNext()){

            Point tp = iter.next();
            //g2d.setColor(new Color(r,g1,b));
            g2d.fillOval((int)tp.getX(), (int)tp.getY(), 10, 10);
        }
    }

    public static void main(String[] args) throws IOException{

        MinimalMouseListenerGUI gui = new MinimalMouseListenerGUI();

        // You always need a frame to place other components such as panels or buttons
        JFrame frame = new JFrame("A minimalistic GUI for drawing");

        // Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Register the gui to listen to mouse events
         */
        gui.addMouseListener(gui);
        gui.addMouseMotionListener(gui);

        // Add an instance of the MinimalDrawingGUI to the frame.
        frame.add(gui);

        // Add the text field and the button to the JPanel (our GUI).

        // Set the size, arrange components, and display the frame.
        frame.pack();
        frame.setVisible(true);

    }


    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub\
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseDragged(MouseEvent arg0) {
        // Empty - do nothing on drag :)

    }


    @Override
    public void mouseMoved(MouseEvent arg0) {
        // Save the mouse coordinates in the member variables x and y
        x =  arg0.getX();
        y =  arg0.getY();
        points.add(new Point(x,y));

        int r = (int) (Math.random()*255)+1;
        int g1 = (int) (Math.random()*255)+1;
        int b = (int) (Math.random()*255)+1;

        colors.add(new Color(r,g1,b));
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    points.clear();
    colors.clear();
    repaint();
    // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }



}

