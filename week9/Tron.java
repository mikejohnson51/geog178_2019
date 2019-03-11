

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* This is not a reference implementation!  */
public class Tron extends JPanel implements KeyListener {

    private static BufferedImage tron;
    private int troncolor = Color.decode("#00ffff").getRGB();

    public Tron() throws IOException{
        super(true);
        this.addKeyListener(this);
        setFocusable(true); // Make sure the keyboard events get directed to the panel.

        tron = ImageIO.read(new File("./tron.png"));

        // ..
        JFrame frame = new JFrame("TronGame");
        frame.add(this);

        setPreferredSize(new Dimension(1000,563));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        move();
        g.drawImage(tron, 0, 0, this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        /// your code (including calling collision())
    }



    public void move(){  // What happens if you call move in the while loop?
    }

    public static void main(String[] args) throws Exception{

        System.out.println(tron.getRGB(5,5));
            Tron gui = new Tron();
        while(true){
            Thread.sleep(0); // control the speed of the game
            gui.repaint();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}

