import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MinimalGUIFileExample extends JPanel{

    // This class stores the image
    private static BufferedImage campus;

    public MinimalGUIFileExample() {
        // Feel free to ignore, this just calls the constructor of JPanel to enable  DoubleBuffering to avoid flickering.
        super(true);

        // Set the (preferred) size of the panel
        setPreferredSize(new Dimension(699,500));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paints all other stuff, e.g., background.

        // All you need to draw the image is the instance of the BufferedImage class.
        g.drawImage(campus, 0, 0, this);


    }
    // throws IOException tells Java that this method may result in an error
    public static void main(String[] args) throws IOException{

        MinimalGUIFileExample gui = new MinimalGUIFileExample();

        // Load the image (this time we do not handle the exception but throw it somewhere else ;-))
        campus = ImageIO.read(new File("campus.png"));

        //Windows users need something like this:
        // "C:\\Users\\You\\GEOG288KJ\\src\\...\\campus.png"

        // You always need a frame to place other components such as panels or buttons
        JFrame frame = new JFrame("A small GUI for loading images and reading files");

        // Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add an instance of the MinimalDrawingGUI to the frame.
        frame.add(gui);

        // Add the text field and the button to the JPanel (our GUI).

        // Set the size, arrange components, and display the frame.
        frame.pack();
        frame.setVisible(true);



    }
}
