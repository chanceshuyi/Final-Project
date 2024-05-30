import java.util.*;

/**
 * A Set is one singular snapshot, scene, or set of the show. 
 * Every set contains a list of marchers, and a corresponding 
 * list of Points that the marchers are at during the 
 * beginning of the set. It also has a private variable referring 
 * to the time between this set and the next (0 at the end of the show)
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

public class Set 
{
    /**
     * The time between this set and the next set in the show
     */
    private double time;

    /**
     * The number of this set in the show
     */
    private int setNumber;

    /**
     * The list of marchers present in this set
     */
    private ArrayList<Marcher> dots;


    /**
     * Constructs a Set with a specified time and set number
     * 
     * @param time The time between this set and the next set
     * @param setNum the number of this set in the show
     */
    public Set(double time, int setNum)
    {
        this.time = time;
        setNumber = setNum;
        dots = new ArrayList<Marcher>();
    }
    
    /**
     * Constructs a Set with a specified time, set number, and list of marchers.
     * 
     * @param time The time between this set and the next set
     * @param setNum The number of this set in the show
     * @param marchers The list of marchers present in this set
     */
    public Set(double time, int setNum, ArrayList<Marcher> marchers) 
    {
        this.time = time;
        setNumber = setNum;
        dots = marchers;
    }

    /**
     * Adds a marcher to the set
     * 
     * @param marcher The marcher to be added to the set
     * @return True if the marcher was successfully added
     */
    public boolean addMarcher(Marcher marcher)
    {
        dots.add(marcher); 

        return true;
    }

    /**
     * Removes a marcher from the set.
     * 
     * @param marcher The marcher to be removed
     * @return True if the marcher was removed successfully, false otherwise
     */
    public boolean removeMarcher(Marcher marcher)
    {
    	// Remove static variable array list marchers from corresponding class
    	if (marcher.isColorguard()) {
        	Colorguard.removeColorguard(Colorguard.marchers.size()-1);
        }
        else {
        	HornMarcher.removeHornMarcher(HornMarcher.marchers.size()-1);
        }
    	
    	// Remove marcher from set 
        if (dots.contains(marcher))
        {
            dots.remove(marcher);
            return true;
        }
        return false;
    }

    /**
     * Changes the coordinates of a marcher in the set
     * 
     * @param marcher The marcher whose coordinates are to be changed
     * @param loc The new location of the marcher
     */
    public void changeCoord(Marcher marcher, Location loc)
    {
        dots.add(marcher);
    }

    /**
     * Changes the time between this set and the next set
     * 
     * @param newTime The new time between this set and the next set
     */
    public void changeTime(double newTime)
    {
        time = newTime;
    }

    /**
     * Changes the number of this set in the show.
     * 
     * @param number The new number of this set
     */
    public void changeNumber(int number)
    {
        setNumber = number;
    }

    /**
     * Gets the time between this set and the next set.
     * 
     * @return The time between this set and the next set
     */
    public Double getTime()
    {
        return time;
    }

    /**
     * Gets the index of this set in the show.
     * 
     * @return The number of this set
     */
    public int getNumber()
    {
        return setNumber;
    }

    /**
     * Gets the list of marchers present in this set
     * 
     * @return The list of marchers present in this set
     */
    public ArrayList<Marcher> getMarchers()
    {
        return dots;
    }
    
    /**
     * Creates a clone of this set.
     * 
     * @return a clone of this set
     * @throws CloneNotSupportedException if the clone operation is not supported
     */
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}