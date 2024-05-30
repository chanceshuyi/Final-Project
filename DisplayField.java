import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The DisplayField class marks the changes between
 * the sets. It calculates the specific step sizes
 * each marcher moves in a frame, and then moves the
 * marchers the correct amount.
 * 
 * @author Chancie Chou
 * @author Rohil Gupta
 * @version 5/23/2024
 * 
 * @author Period - 1
 * @author Assignment - Final Project
 * 
 * @author Sources - Ryan Chew
 */

public class DisplayField
{
    /**
     * The number of sets in the show
     */
    private int numSets;
    /**
     * The field containing the show data
     */
    private Field field;
    /**
     * The show being displayed on the field
     */
    private Show show;
    /**
     * The list of listeners monitoring changes to the DisplayField
     */
    private List<DisplayFieldListener> listeners = new CopyOnWriteArrayList<DisplayFieldListener>();
    /**
     * The current time frame in the show
     */
    private int t = 0;
    /**
     * The current set number
     */
    private int setNum = t / Field.FramesPerSet;
    /**
     * The current frame number within the current set
     */
    private int frameNum = t % Field.FramesPerSet;

    /**
     * Constructs a DisplayField object, setting the field
     * to the Field being used and syncing the show being
     * displayed.
     * 
     * @param inField The field object that contains show dada
     */
    public DisplayField(Field inField){
    	field = inField;
    	show = field.getShow();

        numSets = show.getSets().size();
    }
    
    /**
     * Returns the show that is being displayed.
     * 
     * @return The Show object
     */
    public Show getShow() {
    	return show;
    }
    
    /**
     * Returns the list of marchers from the first set
     * 
     * @return An ArrayList of Marchers
     */
    public ArrayList<Marcher> getMarchers(){
    	return show.getSets().get(0).getMarchers();
    }

    /**
     * Moves the marchers according to their step sizes 
     * and current frame number.
     */
    public void moveMarchers()
    {
    	ArrayList<Marcher> displayMarchers= show.getSets().get(0).getMarchers();
    	
        // Calculate step size
        for (int i=0; i<displayMarchers.size();i++)
        {
            Marcher m = displayMarchers.get(i);
                        
            double[] stepSizes = {0,0};
            
        	if (frameNum!=0)
            {
            	 stepSizes = m.calculateStepSize(setNum); //[StepSizeX, StepSizeY]
            }
            
        	// Starting Location by set number
            Location startingLoc = m.getLoc(setNum);
            
            // Starting + (stepSize * frameNum)
            Location newLoc = new Location((int) Math.round(startingLoc.getX()+(stepSizes[0]*frameNum)), (int) Math.round(startingLoc.getY()+(stepSizes[1]*frameNum))); // New loc = init+stepSize*frameNum
            
            displayMarchers.get(i).move(newLoc);
            
        }
    }

    /**
     * Updates the time and calls the necessary updates to 
     * move marchers and notify the listeners
     */
    public void update()
    {
    	if (t<=(numSets-1)*Field.FramesPerSet) 
        {
    		t++;
            setNum = t/Field.FramesPerSet;
            frameNum = t%Field.FramesPerSet;
            moveMarchers();
            
            fireModelChangedListener();

    	}
        
    }
    
    /**
     * Notifies all the listeners about a change in 
     * the field.
     */
    private void fireModelChangedListener() 
    {
        for (DisplayFieldListener listener : listeners) 
        {
            listener.fieldChanged();
        }
    }
    
    /**
     * Adds a listener to this DisplayField.
     * 
     * @param listener The DisplayFieldListner to be added
     */
    public void addListener( DisplayFieldListener listener) 
    {
        listeners.add(listener);
    }

}