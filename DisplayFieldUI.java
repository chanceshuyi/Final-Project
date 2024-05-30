import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The DisplayFieldUI class sets up and draws
 * the display panel for animations. 
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

public class DisplayFieldUI extends JPanel
{
	/**
	 * The field in which animation is displayed on
	 */
	private DisplayField field;
	/**
	 * The football field picture
	 */
	private Image Pic;
	
	/**
	 * The constructor gets pic as a toolkit image and
	 * converts it to a BufferedImage
	 */
	public DisplayFieldUI()
	{
        Pic = new HelperMethods().toBufferedImage((new ImageIcon(this.getClass().getResource("footballField.jpg"))).getImage());
	}	
	
	/**
	 * Updates the "field" field when the DisplayField changes
	 * 
	 * @param inputField
	 */
	public void setField(DisplayField inputField)
	{
		this.field = inputField;
	}
	
	/**
	 * Paints the football field as well as the marchers
	 * in the animation panel.
	 */
	public void paint(Graphics g)
	{	
        Pic = new HelperMethods().toBufferedImage((new ImageIcon(this.getClass().getResource("footballField.jpg"))).getImage());

	    g.clearRect(0, 0, 720, 400);
	    g.fillRect(0, 0, 720, 400);	    
	
	    // calls function to draw marchers
	    Image marcherPic = (new ImageIcon(this.getClass().getResource("marcherHornsUp.png")).getImage()); // just for initialization
	
	    ArrayList<Marcher> displayMarchers= field.getShow().getSets().get(0).getMarchers();
	    
	    for (Marcher marcher: displayMarchers)
		{ 	// Draw each marcher
	        Location loc = marcher.getLoc();
	        if (marcher.isColorguard())
			{
	            marcherPic = (new ImageIcon(this.getClass().getResource("colorguardFlagUp.png")).getImage()); // if its a colorguard change image
	        }
        	// subtract 15 because the image is 15 pixels to the bottom right
    		Pic.getGraphics().drawImage(marcherPic, loc.getX()-15, loc.getY()-15, this);
    	} 
	
	    g.drawImage(Pic, 0, 0, this);
  }
}