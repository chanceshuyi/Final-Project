import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The AnimationRunner class is responsible for creating
 * and running the animation of the field display. It sets
 * up a timer to update the DisplayField and creates the window
 * that displays the animation.
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
public class AnimationRunner {
	/**
	 * The frame that contains the animation display
	 */
	private JFrame f;
	
	/**
	 * Constructs an AnimationRunner object, creates a timer,
	 * and creates a new DisplayFieldUI so that the animation 
	 * can be drawn.

	 * @param inputField The Field that needs to be animated.
	 */
	public AnimationRunner(Field inputField) {
	    // Create Animation Tab
	    final DisplayFieldUI ui= new DisplayFieldUI();
	    final DisplayField displayField = new DisplayField(inputField);
	    
	    displayField.update();
	    
	    // Infinite Timer
	    Timer timer = new Timer(2000/Field.Speed, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            displayField.update();
	        }
	    });
	    timer.setRepeats(true);
	    timer.start();
	    
	    // Event Listener for updates: On update call repaint
	    displayField.addListener(new DisplayFieldListener() {
	    	@Override
	    	public void fieldChanged() {
	    		ui.repaint();
	    	}
	    });
	    ui.setField(displayField);
	    
	    // Window
		f = new JFrame();
	    f.setTitle("Animation Display");
	    f.add(ui);
	    f.setSize(700,420);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	    f.setResizable(false);

	    f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
}