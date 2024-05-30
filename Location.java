/**
 * The Location class represents a point with x and y coordinates.
 * It provides methods to get and set the coordinates.
 * 
 * @author Chancie Chou
 * @author Rohil Gupta
 * 
 * @author Period - 1
 * @author Assignment - Final Project
 * 
 * @version 5/22/2024
 * @author Sources - none
 */
public class Location 
{
    private int x;
    private int y;

    /**
     * Constructs a new Location with the specified x and y coordinates.
     * 
     * @param x the x-coordinate of the location
     * @param y the y-coordinate of the location
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the location.
     * 
     * @return the x-coordinate
     */
    public int getX()
    {
        return x;
    }

    /**
     * Returns the y-coordinate of the location.
     * 
     * @return the y-coordinate
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Sets the x-coordinate of the location.
     * 
     * @param xNew the new x-coordinate
     */
    public void setX(int xNew) {
        x = xNew;
    }
    
    /**
     * Sets the y-coordinate of the location.
     * 
     * @param yNew the new y-coordinate
     */
    public void setY(int yNew) {
        y = yNew;
    }
}
