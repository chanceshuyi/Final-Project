import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The HornMarcher is a child of the Marcher class 
 * and represents a player holding an instrument.
 * 
 * @author Chancie Chou
 * @author Rohil Gupta
 * @version 5/23/2024
 * 
 * @author Period - 1
 * @author Assignment - Final Project
 * 
 * @author Sources - none
 */

public class HornMarcher extends Marcher
{

    /**
     * Static counter for the number of HornMarchers.
     */
    static int numHorn = 0;

    /**
     * List of all HornMarchers.
     */
    static ArrayList<Marcher> marchers = new ArrayList<Marcher>();
    
    /**
     * Default constructor for HornMarcher which sets the horn to down and adds this to marchers list.
     */
    public HornMarcher()
    {
        super();
        marchers.add(this);
    }
    
    /**
     * Constructor for HornMarcher with specified location which sets the horns to down and adds this to marchers list.
     * 
     * @param loc Initial location of the HornMarcher.
     */
    public HornMarcher(Location loc)
    {
        super(loc);
        marchers.add(this);
        
    }
    
    /**
     * Removes the HornMarcher at the specified index from the list of marchers.
     * 
     * @param index The index of the HornMarcher to remove.
     */
    public static void removeHornMarcher(int index) {
    	marchers.remove(index);
    	System.out.println("HornMarcher");
    }
    
    /**
     * Paints the HornMarcher on the field with the appropriate HornMarcher image based on the horns position.
     * 
     * @param g The Graphics object that does the painting
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Image pic = null;
        super.paintComponent(g);

        ImageIcon image = new ImageIcon("marcherHornsUp.png");
        pic = image.getImage();

        g.drawImage(pic, getLoc().getX(), getLoc().getY(), this);

    }

}