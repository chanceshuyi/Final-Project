import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The Colorguard is a child of the Marcher class 
 * which represents a Colorguard member of the show. 
 * Colorguard actors have a different image from HornMarcher, 
 * and is able to lift their flag up or down. 
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

public class Colorguard extends Marcher
{

    /**
     * List of all the marchers that are colorguard
     */
    static ArrayList<Marcher> marchers = new ArrayList<Marcher>();

    /**
     * Default constructor for Colorguard which sets the flag to down and adds it to the list of marchers
     */
    public Colorguard()
    {
        super();
        marchers.add(this);
    }
    
    /**
     * Constructor for Colorguard with a specified location, sets the flag to down and adds this to marchers list.
     * 
     * @param loc Initial location of the Colorguard
     */
    public Colorguard(Location loc)
    {
        super(loc);
        marchers.add(this);
    }
    
    /**
     * Removes the colorguard at the specified index from the list of marchers.
     * 
     * @param index The index of the Colorguard to remove
     */
    public static void removeColorguard(int index) {
    	marchers.remove(index);
    }


    /**
     * Paints the colorguard at the location specified
     * 
     * @param g The graphics object that does the painting
     * @param x The x-coordinate where the image will be drawn
     * @param y The y-coordinate where the image will be drawn
     */
    public void paintComponent(Graphics g, int x, int y)
    {
        Image pic = null;
        super.paintComponent(g);
        
        ImageIcon image = new ImageIcon("colorguardFlagUp.png");
        pic = image.getImage();

        g.drawImage(pic, x, y, null);
    }

    /**
     * Indicates that this marcher is a Colorguard
     * 
     * @return true, because this is a Colorguard
     */
    @Override
    public boolean isColorguard() {
        return true;
    }
}