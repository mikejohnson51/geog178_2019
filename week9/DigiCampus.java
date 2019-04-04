/**************************************************************************************************
 *
 *
 * @author Natasha Krell and Anagha Uppal
 * Mar 5, 2019
 *
 *This is our first attempt at producing a small visual that digitizes points and paths on UCSB campus.
 * Certain points of interest on UCSB campus are pre-defined and it displays their names if points or paths are close
 * You can also add points yourself into this set.
 * Right click to clear all points from screen.
 ***************************************************************************************************
 */

// Still needs doing:
// INTERSECTIONS!

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DigiCampus extends JPanel implements ActionListener, MouseListener, MouseMotionListener {


    // These variables will store the mouse coordinates for adding points
    int x = 0, y = 0;
    // ArrayList<Point> points = new ArrayList<Point>();
    Polyline lines = new Polyline();
    ArrayList<POI> points = new ArrayList<POI>();
    POI tmpPOI = new POI(0, 0, "test");
    //ArrayList<Polyline> polylines = new ArrayList<>();
    ArrayList<PointBuffer> streets = new ArrayList<PointBuffer>();
    ArrayList<PointBuffer> streets_test = new ArrayList<PointBuffer>(10);

    //Nodes nodes = new Nodes();
    //Lines streets = new Lines();


    // Menu item declarations
    private JMenuItem MakePoints, MakePath, MakeStreets, openMenuItem, saveMenuItem, saveAsMenuItem;
    private JMenu menu, fileMenu;
    private JMenuBar menuBar;
    private boolean checkPoints, checkPath, checkStreets; // this is to check which menu item is checked - stuff done accordingly


    // Input dialog
    JOptionPane inputPane = new JOptionPane();

    // Store the image
    private static BufferedImage campus;

    public DigiCampus() {
        super(true);
        setPreferredSize(new Dimension(699, 530)); // added space here to add text field
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paints all other stuff, e.g., background.

        // add text
        g.drawString("Welcome to DigiCampus!", 10, 500);
        g.drawString("Right click to clear all points from screen.", 10, 520);

        // drawing image
        g.drawImage(campus, 0, 0, this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ArrayList<Point> intersections = new ArrayList<>();

        // drawing points
        if (points.size() >= 1) {
            for (int i = 0; i < points.size(); i++) { // for each in points

                PointBuffer pb = new PointBuffer(points.get(i), 30); // buffer to show label
                //for (int j = 0; j < i; j++) { // check point against set of all pre-existing defined points with names
                //if (pb.isInside(points.get(j))) break;

                //    if (pb.isInside(POI.get(j))) { // if a POI matches our point or is close (think: tour guide)
                //      g2d.setColor(Color.darkGray); // display its corresponding label
                //    g2d.drawString(j, (int) lines.get(i).getX() + 3, (int) lines.get(i).getY() - 3);
                g2d.setColor(Color.black);
                // draw point
                g2d.fillOval((int) points.get(i).getX(), (int) points.get(i).getY(), 10, 10);
                g2d.drawString(points.get(i).getName(), (int) points.get(i).getX(), (int) points.get(i).getY() - 5);

                if (streets.size() != 0) {
                    double mindist_streets = Double.MAX_VALUE;
                    double mindist_intersections = Double.MAX_VALUE;
                    Point min_streets = new Point(0, 0);
                    Point min_intersections = new Point(0, 0);
                    Point minPoint = new Point(0, 0);

                    for (int j = 0; j < streets.size(); j++) {
                        if (points.get(i).distance(streets.get(j).getCentre()) < mindist_streets) {
                            mindist_streets = points.get(i).distance(streets.get(j).getCentre());
                            min_streets = streets.get(j).getCentre();

                        }
                    }


                    for (int j = 0; j < intersections.size(); j++) {
                        if (points.get(i).distance(intersections.get(j)) < mindist_intersections) {
                            mindist_intersections = intersections.get(i).distance(intersections.get(j));
                            min_intersections = intersections.get(j);
                        }

                        //minPoint.displayPoint();

                        //}
                    }

                    if (mindist_streets < mindist_intersections)
                        minPoint = min_streets;

                    else
                        minPoint = min_intersections;

                    g2d.drawLine(
                            (int) (points.get(i)).getX(),
                            (int) (points.get(i)).getY(),
                            (int) (minPoint.getX()),
                            (int) (minPoint.getY())
                    );
                }
            }
        }
        //}
        // }

        // drawing lines
        if (lines.size() >= 1) {
            for (int i = 0; i < lines.size(); i++) { // for each in points
                g2d.setColor(Color.black);
                // draw point
                g2d.fillOval((int) lines.get(i).getX(), (int) lines.get(i).getY(), 10, 10);
                PointBuffer pb = new PointBuffer(lines.get(i), 20); // buffer to show label
                //  for (String j : POI.keySet()) { // check point against set of all pre-existing defined points with names
                //    if (pb.isInside(POI.get(j))) { // if a POI matches our point or is close (think: tour guide)
                //      g2d.setColor(Color.darkGray); // display its corresponding label
                //    g2d.drawString(j, (int) lines.get(i).getX() + 3, (int) lines.get(i).getY() - 3);

            }

        }


        //   }
        // }

        //}
        //if (checkPath == true) { // only if line mode is on
        if (lines.size() >= 2) { // and if there are at least two points in the set to draw a line between
            g2d.setColor(Color.red); // draw a path between points

            for (int i = 0; i < lines.size() - 1; i++) {
                g2d.drawLine(
                        (int) (lines.get(i)).getX(),
                        (int) (lines.get(i)).getY(),
                        (int) (lines.get(i + 1)).getX(),
                        (int) (lines.get(i + 1)).getY()
                );
                //
                // for (String j : POI.keySet()) { // check area around line against set of all pre-existing POIs
                //    if (lb.isInside(POI.get(j))) { // if a POI is close to drawn path (think: tour guide)
                //       g2d.setColor(Color.gray); // display POI's corresponding label
                //      g2d.drawString(j, (int) POI.get(j).getX() + 3, (int) POI.get(j).getY() - 3);
                //   }
                // }

            }
        }

        // drawing streets
        for (int i = 0; i < streets.size(); i++) {
            g2d.setColor(Color.black);
            g2d.fillOval((int) streets.get(i).getCentre().getX() -5, (int) streets.get(i).getCentre().getY() -5, 10, 10);

        }

        Lines line = new Lines();
        line.buildNodes();

        if (streets.size() >= 2) { // and if there are at least two points in the set to draw a line between
            g2d.setColor(Color.red); // draw a path between points
            System.out.println("streets size " + streets.size());
            for (int i = 0; i < streets.size()-1; i++) {
                line.add(new Line(streets.get(i).getCentre(), streets.get(i+1).getCentre()));
                System.out.println(" i " + i + " " + i+1);
            }
            int initLineSize = line.size();
            System.out.println("line size " + initLineSize);
            for (int i = 0; i < line.size(); i++) {
                System.out.println(i);
                line.get(i).getS().displayPoint();
                line.get(i).getE().displayPoint();
                g2d.drawLine(
                        (int) (line.get(i).getS()).getX(),
                        (int) (line.get(i).getS()).getY(),
                        (int) (line.get(i).getE()).getX(),
                        (int) (line.get(i).getE()).getY()
                );
            }
            ArrayList<Integer> intersecting_lines = new ArrayList<>();
            // check for intersections
            for (int i = 0; i < line.size(); i++) {
                for (int j = 0; j < line.size(); j++) {
                    Point intersection = LineIntersection.LineIntersection(line.get(i).getS(),
                            line.get(i).getE(), line.get(j).getS(), line.get(j).getE());
                    if (intersection.getX() != Double.MAX_VALUE && intersection.getY() != Double.MAX_VALUE
                    && intersection.getX() != line.get(i).getS().getX() && intersection.getX() != line.get(i).getE().getX()
                    && line.get(i).isInside(intersection) &&  line.get(j).isInside(intersection)) {
                        intersections.add(intersection);
                        intersecting_lines.add(i);
                        intersecting_lines.add(j);
                        System.out.println("yes");
                         g2d.setColor(Color.blue);
                        g2d.fillOval((int) intersection.getX()-5, (int) intersection.getY()-5, 10, 10);
                        //line.get(i).getE() = intersection;
                        //line.get(j).getE() = intersection;
                        //intersections.get(i).displayPoint();
                    }


                }
            }
            for (int i = 0; i < intersections.size(); i++) {
                intersections.get(i).displayPoint();
            // add new lines
                line.add(new Line(line.get(intersecting_lines.get(i+i)).getS(),intersections.get(i)));
                line.add(new Line(line.get(intersecting_lines.get(i+i+1)).getS(),intersections.get(i)));
                line.add(new Line(line.get(intersecting_lines.get(i+i)).getE(),intersections.get(i)));
                line.add(new Line(line.get(intersecting_lines.get(i+i+1)).getE(),intersections.get(i)));
                // delete old lines
                // there is no existing method to remove a line from lines arraylist :/
            }
            for (int i = initLineSize; i < line.size(); i++) {

                g2d.setColor(Color.pink);
                g2d.drawLine(
                        (int) (line.get(i).getS()).getX(),
                        (int) (line.get(i).getS()).getY(),
                        (int) (line.get(i).getE()).getX(),
                        (int) (line.get(i).getE()).getY()
                );
            }

        }
    }


    // throws IOException tells Java that this method may result in an error
    public static void main(String[] args) throws IOException, URISyntaxException { // what does URIException do??

        //super(true); // can maybe ignore

        DigiCampus gui = new DigiCampus();

        // Load the image (this time we do not handle the exception but throw it somewhere else ;-))
        campus = ImageIO.read(new File("campus.png"));

        // You always need a frame to place other components such as panels or buttons
        JFrame frame = new JFrame("A small digitization GUI example for UCSB campus");

        // Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // new gui objects
        gui.menuBar = new JMenuBar();

        gui.fileMenu = new JMenu("File");
        gui.openMenuItem = new JMenuItem("Open...");
        gui.saveMenuItem = new JMenuItem("Save");
        gui.saveAsMenuItem = new JMenuItem("Save As...");
        gui.saveMenuItem.setEnabled(false);

        gui.menu = new JMenu("Edit");
        gui.MakePoints = new JMenuItem("Make Points of Interest");
        gui.MakePath = new JMenuItem("Make Paths");
        gui.MakeStreets = new JMenuItem("Make Streets");

        // add menu items
        gui.menu.add(gui.MakePoints);
        gui.menu.add(gui.MakePath);
        gui.menu.add(gui.MakeStreets);

        gui.fileMenu.add(gui.openMenuItem);
        gui.fileMenu.add(new JSeparator());
        gui.fileMenu.add(gui.saveMenuItem);
        gui.fileMenu.add(gui.saveAsMenuItem);

        // changed the order of tabs
        // changed the name of the tab from Modes to Edit.
        gui.menuBar.add(gui.fileMenu);
        gui.menuBar.add(gui.menu);

        gui.addMouseListener(gui);
        gui.addMouseMotionListener(gui);
        gui.MakePath.addActionListener(gui);
        gui.MakePoints.addActionListener(gui);
        gui.MakeStreets.addActionListener(gui);
        gui.openMenuItem.addActionListener(gui);
        gui.saveMenuItem.addActionListener(gui);
        gui.saveAsMenuItem.addActionListener(gui);

        gui.menu.addActionListener(gui);
        gui.add(gui.menuBar);

        // Add an instance of the MinimalDrawingGUI to the frame.

        frame.setJMenuBar(gui.menuBar); // add menu bar to frame
        frame.add(gui);

        // Add the text field and the button to the JPanel (our GUI).

        // Set the size, arrange components, and display the frame.
        frame.pack();
        frame.setVisible(true);


    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Empty - do nothing on drag :)
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { // right click to clear screen
            points = new ArrayList<POI>();
            lines = new Polyline();
            streets = new ArrayList<PointBuffer>();

            // use distance method with a radius to meet criteria that labels shows if path comes close and label shows if you click on it
            // so if within the buffer does those two things.
            repaint();
        }
        if (e.getButton() == MouseEvent.BUTTON1) { // to add points

            x = e.getX();
            y = e.getY();

            if (checkPoints) { // only if in POI mode
                String input = inputPane.showInputDialog("Point of interest name: ");
                points.add(new POI(x, y, input)); // add to points array

            } else if (checkPath) // add to lines array
                lines.add(new Point(x, y));


            else if (checkStreets)
                streets.add(new PointBuffer(new Point(x, y)));

        }

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        // Code for menu items
        if (e.getSource() == openMenuItem) {
            System.out.println("User initiated open function.");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Open");

            int fileChooserResult = fileChooser.showOpenDialog(fileChooser); // changed frame to fileChooser (?) EDIT HERE.

            if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
                File openPath = fileChooser.getSelectedFile();

                try {
                    FileInputStream fileIn = new FileInputStream(openPath.getAbsolutePath());
                    ObjectInputStream objIn = new ObjectInputStream(fileIn);

                    // made some edits here to Mike's code
                    SaveFile saveFile = (SaveFile) objIn.readObject();
                    streets = saveFile.getStreets();
                    //Line pp = saveFile.getPp();
                    //Polyline pl = saveFile.getPl();

                    objIn.close();
                    fileIn.close();

                    String saveFilePath = openPath.getAbsolutePath();
                    saveMenuItem.setEnabled(true);
                    fileSaved(); // had to create this method -- not sure if it needs more work

                    repaint();

                    System.out.println("Opened file: "+ saveFilePath);

                } catch (FileNotFoundException i) {
                    System.out.println("Open failed!");

                    JOptionPane.showMessageDialog(fileMenu, "Could not find a file!", "No File Found", JOptionPane.ERROR_MESSAGE);
                } catch (IOException i) {
                    System.out.println("Open failed!");
                    JOptionPane.showMessageDialog(fileMenu,  "The file you selected is not compatible with DigiCampus", "Invalid File", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException i) {
                    System.out.println("Open failed!");
                    JOptionPane.showMessageDialog(fileMenu, "The file you selected is not compatible with DigiCampus", "Invalid File", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Open aborted!");
            }

            // Code for saving a file

        } else if (e.getSource() == saveAsMenuItem) {
            System.out.println("User initiated save as function.");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save As");
            File file = new File(System.getProperty("user.home")+java.io.File.separator+"MyData.tgis");
            fileChooser.setSelectedFile(file);
            int fileChooserResult = fileChooser.showSaveDialog(saveAsMenuItem);

            if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
                File savePath = fileChooser.getSelectedFile();

                try {
                    FileOutputStream fileOut = new FileOutputStream(savePath.getAbsolutePath());
                    ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

                    // This needs to be edited when we have the nodes and lines that we want to save in the file.
                    // add the rest of the code from slide 5 on section PDF

                    SaveFile saveFile = new SaveFile(streets); // whatever you want to read in should go here: probably nodes and lines.
                    objOut.writeObject(saveFile);

                    objOut.close();
                    fileOut.close();

                    String saveFilePath = savePath.getAbsolutePath();
                    saveMenuItem.setEnabled(true);
                    fileSaved();

                    System.out.println("Saved as file: " +saveFilePath);

                } catch(IOException i) {
                    System.out.println("Save as failed!");
                    JOptionPane.showMessageDialog(saveAsMenuItem, "There was an error in saving your data.", "Save Error", JOptionPane.ERROR_MESSAGE); // instead of frame used saveAsMenuItem
                }

            } else {
                System.out.println("Save as aborted.");
            }
        }//}


        // Code for modes
        if (e.getSource() == MakePoints) { // if in POI mode
            //streets = new ArrayList<Point>();
            lines = new Polyline();

            checkPoints = true;
            checkPath = false;
            checkStreets = false;
            //   if (arg0.getSource() == POIButton) {
            //     POI.put(poiField.getText(), points.get(points.size() - 1)); // if add POI button is clicked
            //}
        }
        if (e.getSource() == MakePath) {
            points = new ArrayList<POI>();
            streets = new ArrayList<PointBuffer>();

            //Point pn = points.get(points.size() - 1);
            //int rad = 60;
            //PointBuffer pb1= new PointBuffer(pn, rad);

            //if(pb1.isInside(pn) == true) {
            //	 for (int i = 0; i < lines.size(); i++) {
            //		points.get(points.size()- 1).setX((int) (points.get(i)).getX());
            //		points.get(points.size()- 1).setY((int) (points.get(i)).getY());

            //POI.put(poiField.getText(),points.get(points.size()-1));
            //points.clear();
            checkPath = true;
            checkPoints = false;
            checkStreets = false;


            //if (arg0.getSource() == POIButton) {
            //    POI.put(poiField.getText(), lines.get(lines.size() - 1)); // if add POI button is clicked
            //}
        }
        //}

        if (e.getSource() == MakeStreets) {
            points = new ArrayList<POI>();
            lines = new Polyline();

            checkStreets = true;
            checkPath = false;
            checkPoints = false;

        }
        //checkRun = true;
        //poiField.setVisible(true); // how to set visible POI textfield
        //if (arg0.getSource() == POIButton) {
        //  POI.put(poiField.getText(), points.get(points.size() - 1)); // if add POI button is clicked
        repaint();

        //      repaint();

        // Read and write objects

        try{
            FileOutputStream fout = new FileOutputStream("./points");
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            Iterator<POI> it = points.iterator();
            while (it.hasNext()) {
                POI aPoint = it.next();
                oout.writeObject(aPoint);
            }
            //oout.defaultWriteObject();
            oout.close();
            fout.close();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }}


    // this is needed to work but I don't know why!
    private void fileSaved() {
        // TODO Auto-generated method stub

    }
}