import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * This class contains helper methods that are used
 * across multiple classes. 
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
public class HelperMethods {
	/**
	 * Converts a given Image to a BufferedImage.
	 * 
     * If the provided image is already a BufferedImage, it is cast and returned directly.
     * Otherwise, a new BufferedImage is created with the dimensions and type of the provided image, and the image is drawn onto this BufferedImage.
     * @param image The image to be converted to a BufferedImage
     * @return The converted BufferedImage
	 */
	public BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage) image;
	    }

	    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
	            BufferedImage.TYPE_INT_RGB);
	    Graphics g = bi.getGraphics();
	    g.drawImage(image, 0, 0, null);
	    g.dispose();
	    
	    return bi;
	}
}
