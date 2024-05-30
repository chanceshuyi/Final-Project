import java.util.*;

/**
 * The Show is represented by a LinkedList of sets 
 * in the show, from beginning to end.
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

public class Show 
{
    /**
     * The list of sets in the show.
     */
    private ArrayList<Set> sets;
    
    /**
     * Constructs a new Show with an initial set
     */
    public Show()
    {
        sets = new ArrayList<Set>();
        sets.add(new Set(1.0, 1));
    }

    /**
     * Changes a set at a specified index.
     * 
     * @param index The index of the set to be changed
     * @param set The new set to be placed at the specified index
     */
    public void changeSet(int index, Set set)
    {
        sets.set(index, set);
    }

    /**
     * Adds a new set to the show.
     * 
     * @param set The set to be added
     * @return true if the set was added successfully
     */
    public boolean addSet(Set set)
    {
        //System.out.println("adding");
        sets.add(set); //add set at index n
        for (int i =0; i<sets.get(0).getMarchers().size(); i++){
            sets.get(0).getMarchers().get(i).updateSets(this);
        }
        for (Marcher m: set.getMarchers()) { //update all sets for marchers
        	m.updateSets(this);
        }
        return true;
        
    }

    /**
     * Deletes a set at a specified index.
     * 
     * @param n the index of the set to be deleted
     * @return true if the set was deleted successfully, false otherwise
     */
    public boolean deleteSet(int n)
    {
        if (!sets.isEmpty()) {
            sets.remove(n); // Delete set at index n
            return true;
        }
        return false;
    }

    /**
     * Gets the list of sets in the show.
     * 
     * @return the list of sets
     */
    public ArrayList<Set> getSets()
    {
        return sets;
    }
}