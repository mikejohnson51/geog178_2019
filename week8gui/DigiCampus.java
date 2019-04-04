import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.HashMap;
import java.util.Scanner;


public class DigiCampus extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    // These variables will store the mouse coordinates
    int x=0,y=0;
    Polyline points = new Polyline();
    //ArrayList<Point> POI = new ArrayList<>();
    //ArrayList<Polyline> polylines = new ArrayList<>();

    HashMap<String, Point> POI = new HashMap<>();

    private JButton POIButton =  new JButton("Add POI");
    private JTextField poiField, showField;



    private boolean checkRun = false; // whether to enter POI mode


    // This class stores the image
    private static BufferedImage campus;

    public DigiCampus() {
        super(true);

        setPreferredSize(new Dimension(699,450));

        POI interestPoints = new POI(); // ???


        POI.put("Rec Center", new Point(233,21));
        POI.put("Athletic Fields", new Point(198,74));
        POI.put("Rob Gym", new Point(235,135));
        POI.put("Student Affairs", new Point(337,119));
        POI.put("Ellison", new Point(422,168));
        POI.put("Buchanan", new Point(408,196));
        POI.put("Phelps", new Point(464,146));
        POI.put("HSSB", new Point(129,280));
        POI.put("Counseling and Career", new Point(232,303));
        POI.put("Education", new Point(215,206));
        POI.put("Library", new Point(407,288));
        POI.put("SRB", new Point(88,335));
        POI.put("Performing Arts", new Point(160,310));
        POI.put("UCEN", new Point(297,400));
        POI.put("MCC", new Point(358,408));
        POI.put("Psych", new Point(433,384));
        POI.put("Life Sciences", new Point(494,389));
        POI.put("Bren", new Point(566,314));
        POI.put("Arbor", new Point(360,265));
        POI.put("Elings", new Point(655,183));
        POI.put("Bus Loop", new Point(280,195));

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paints all other stuff, e.g., background.

        // All you need to draw the image is the instance of the BufferedImage class.
        g.drawImage(campus, 0, 0, this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        for (int i = 0; i < points.size(); i++) {
            g2d.setColor(Color.black);

            g2d.fillOval((int) points.get(i).getX(), (int) points.get(i).getY(), 10, 10);
            PointBuffer pb = new PointBuffer(points.get(i), 20);
            for (String j : POI.keySet()) {
                if (pb.isInside(POI.get(j))) {
                    g2d.setColor(Color.darkGray);
                    g2d.drawString(j, (int) points.get(i).getX()+3, (int) points.get(i).getY()-3);


                }

            }

        }
        if (points.size() >= 2) {
            g2d.setColor(Color.red);

            for (int i = 0; i < points.size() - 1; i++) {
                g2d.drawLine(
                        (int) (points.get(i)).getX(),
                        (int) (points.get(i)).getY(),
                        (int) (points.get(i + 1)).getX(),
                        (int) (points.get(i + 1)).getY()
                );
                LineBuffer lb = new LineBuffer(points.get(i), points.get(i + 1));
                for (String j : POI.keySet()) {
                    if (lb.isInside(POI.get(j))) {
                        g2d.setColor(Color.gray);
                        g2d.drawString(j, (int) POI.get(j).getX()+3, (int) POI.get(j).getY()-3);

                    }
                }
            }


        }

        if (checkRun) {
            //
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        checkRun = true;
        POI.put(poiField.getText(),points.get(points.size()-1));

// How to take in based on enter instead of button? ??
        /*Scanner scanner = new Scanner(System.in);
        String readString = scanner.nextLine();
        while(readString!=null) {
            System.out.println(readString);
            POI.put(readString,points.get(points.size()-1));

            if (readString.isEmpty()) {
                System.out.println("Read Enter Key.");
            }

            if (scanner.hasNextLine()) {
                readString = scanner.nextLine();
            } else {
                readString = null;
            }
        }*/
        checkRun = false;
        repaint();
    }


        // throws IOException tells Java that this method may result in an error
    public static void main(String[] args) throws IOException{

        DigiCampus gui = new DigiCampus();

        // Load the image (this time we do not handle the exception but throw it somewhere else ;-))
        campus = ImageIO.read(new File("campus.png"));

        //Windows users need something like this:
        // "C:\\Users\\You\\GEOG288KJ\\src\\...\\campus.png"

        // You always need a frame to place other components such as panels or buttons
        JFrame frame = new JFrame("A small digitization GUI example for UCSB campus");

        // Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui.poiField =  new JTextField("Enter name of this place");




        gui.addMouseListener(gui);
        gui.addMouseMotionListener(gui);
        gui.POIButton.addActionListener(gui);
        gui.add(gui.POIButton);
        gui.add(gui.poiField); // what I really want to do is add an if statement if pressed - show field ??
        // setVisible(true)



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

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3){
            Polyline points = new Polyline();
            HashMap<String, Point> POI = new HashMap<>();
            repaint();
            System.out.println("true"); // ???
        }
        if (e.getButton() == MouseEvent.BUTTON1) {

            x = e.getX();
            y = e.getY();
            points.add(new Point(x, y));
        }
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
