import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The base class that everything on the field will 
 * extend or instantiate. Similar to the Actor class 
 * in Gridworld, it implements the basic common features 
 * between all marchers. The constructor and method to 
 * move are included. 
 * 
 * @author Chancie Chou
 * @author Rohil Gupta
 * @version 5/23/2024
 * 
 * @author Period - 1
 * @author Assignment - Final Project
 * 
 * @author Sources
 */

public class Marcher extends JComponent{ //implements ImageObserver //implements ActionListener
    
    /**
     * The current location of the marcher
     */
    private Location loc;

    /**
     * List of locations for each set
     */
    private ArrayList<Location> locationList = new ArrayList<Location>();

    /**
     * Image representing the marcher (default is the marcherHornsUp.png)
     */
    Image Pic = (new ImageIcon("marcherHornsUp.png")).getImage();

    /**
     * Default constructor with no parameters.
     * If no location is passed, sets the initial
     * location to (20, 20).
     */
    public Marcher()
    {     
        loc = new Location(20,20);
        locationList.add(loc);
    }

    /**
     * Constructor with location parameter
     * @param location The initial location of the marcher
     */
    public Marcher(Location location)
    {
        loc = location;        
        locationList.add(loc);
    }

    /**
     * Moves the marcher to a new location
     * @param newloc The new location to move to
     */
    public void move(Location newloc)
    {
        loc = newloc;
    }

    /**
     * Moves the marcher to a new location in a specific set.
     * @param newloc The new location to move to
     * @param setNum The set number where there marcher is moved
     */
    public void move(Location newloc, int setNum)
    {
        // Set new location in set
        locationList.get(setNum).setX(newloc.getX());
        locationList.get(setNum).setY(newloc.getY());
    }

    /**
     * Updates the locations for each set based on the given show
     * @param show The show containing the sets to update
     */
    public void updateSets(Show show) {
    	int numSets = show.getSets().size();

        while (locationList.size() < numSets){ // Loop amount (difference between num locations and total num sets)
            Location last = locationList.get(locationList.size()-1);
            locationList.add(new Location(last.getX(), last.getY())); // duplicates last one in locationList
        }
    }

    /**
     * Returns the current location of the marcher
     * @return The current location of the marcher
     */
    public Location getLoc() 
    {
        return loc;
    }

    /** 
     * Returns the locationList of the current marcher
     * @return Current Marcher's list of locations
     */
    public ArrayList<Location> getLocs() 
    {
        return locationList;
    }


    /**
     * Returns the location of the marcher at a specific set.
     * @param setNum The set number
     * @return The location at the specified set
     */
    public Location getLoc(int setNum)
    {
        return locationList.get(setNum);
    }

    /**
     * Calculates the step size between the current and next
     * location for a given set index.

     * @param setIndex The index of the set to calculate the step size for
     * @return An array containing the step sizes in the x and y directions
     */
    public double[] calculateStepSize(int setIndex){
        // System.out.println(setIndex);
        // System.out.println(locationList.size());

        Location startingLoc = locationList.get(setIndex);
        
        if( setIndex + 1 != locationList.size())
        {
            Location nextLoc = locationList.get(setIndex+1);
        
            double deltaX = nextLoc.getX() - startingLoc.getX(); // delta = next-initial
            double deltaY = nextLoc.getY() - startingLoc.getY();

            double[] output = {deltaX/Field.FramesPerSet, deltaY/Field.FramesPerSet}; // [StepX, StepY]
            return output;
        }

        return (new double[]{0,0});

    }

    /**
     * Paints the component by drawing the marcher's image.
     * @param g The Graphics object that does the drawing of the marcher
     */
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(Pic, 0, 0, this);
        this.setVisible(true);
    }

    /**
     * Indicates if the marcher is a colorguard member
     * @return false by default, as the base marcher defaults to a hornMarcher image
     */
    public boolean isColorguard()
    {
        return false;
    }

    // /**
    //  * Indicates if the marcher has their horns up.
    //  * @return - False by default
    //  */
    // public boolean hornsUp()
    // {
    //     return false;
    // }

    /**
     * Adds a new location to the list of locations
     * @param l The location to add.
     */
    public void addLoc(Location l)
    {
        locationList.add(l);
    }

    
}