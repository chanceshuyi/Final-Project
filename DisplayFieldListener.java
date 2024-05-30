/**
 * The DisplayFieldListner is an interface used in 
 * AnimationRunner. A DisplayFieldListener listens 
 * to updates from the DisplayField after each frame 
 * change.
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

public interface DisplayFieldListener {
	/**
	 * An abstract class that indicates when the frame
	 * number in DisplayField is changed.
	 */
	void fieldChanged();
}