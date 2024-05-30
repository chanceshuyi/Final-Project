import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The FieldEditor class creates a GUI frame with many
 * buttons that allows users to add and remove marchers, 
 * play/stop the show, and to navigate between sets. It 
 * also allows users to create a new set, and determine
 * the time for each set.
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

public class FieldEditor extends JFrame 
{
    /**
     * The main wrapper panel for the buttons
     */
    private JPanel controlPanel; // this is like the main wrapper for the buttons
    /**
     * The field created by this editor
     */
    private Field field;
    /**
     * The current set number being edited.
     */
    private int setNum;
    /**
     * Label to display the current set number.
     */
    private JLabel displaySetNumLabel;

    /**
     * Constructor for the FieldEditor class.
     * Initializes the GUI buttons and sets up event listeners.
     * 
     * @param f The field to be edited.
     */
    public FieldEditor(Field f) {
        setNum = 0; // Set Number
        field = f;

        setTitle("Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); 

        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));


        // marcher related buttons
        JPanel row1 = new JPanel(new FlowLayout());

        // Add Marcher
        JButton addMarcherButton = new JButton("Add Marcher");
        addMarcherButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Marcher newMarcher = new HornMarcher();
                // Add to every set in field
                for (int i = 0; i < field.getShow().getSets().size(); i++) {
                    // reference current set + add marcher
                    Set setDummy = field.getShow().getSets().get(i);
                    
                    if (i == 0)
                    {
                        setDummy.addMarcher(newMarcher);
                    }

                    else
                    {
                        setDummy.addMarcher(newMarcher);
                        newMarcher.updateSets(field.getShow());
                    }

                    // change set within show
                    field.getShow().changeSet(i, setDummy);
                }
                field.repaint();
            }
        });
        row1.add(addMarcherButton);

        // Remove Marcher
        JButton removeMarcherButton = new JButton("Remove Marcher");
        removeMarcherButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove from every set in field
                for (int i = 0; i < field.getShow().getSets().size(); i++) {
                    // reference current set + remove marcher
                    Set setDummy = field.getShow().getSets().get(i);


                    if (f.getSelection() != null ) { // Make sure there is a selection
            			if (!f.getSelection().isColorguard()){
            			setDummy.removeMarcher(f.getSelection()); // Remove selected marcher
            			// change set within show
                		field.getShow().changeSet(i, setDummy);
                		field.removeSelection();
                		
                		field.repaint();
            		} else{
            			System.out.println("Must Select a Horn Marcher");
            			}	
            		} else {
            			System.out.println("Please make a selection :)");
            		}

                }
            }
        });
        row1.add(removeMarcherButton);
        controlPanel.add(row1);

        // colorguard related buttons
        JPanel row2 = new JPanel(new FlowLayout());

        JButton addColorGuardButton = new JButton("Add Color Guard");
        addColorGuardButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Marcher newMarcher = new Colorguard();
                // Add to every set in field
                for (int i = 0; i < field.getShow().getSets().size(); i++) {
                    // reference current set + add marcher
                    Set setDummy = field.getShow().getSets().get(i);
                    
                    if (i == 0)
                    {
                        setDummy.addMarcher(newMarcher);
                    }

                    else
                    {
                        setDummy.addMarcher(newMarcher);
                        newMarcher.updateSets(field.getShow());
                    }

                    // change set within show
                    field.getShow().changeSet(i, setDummy);
                }
                field.repaint();
            }
        });
        row2.add(addColorGuardButton);

        JButton removeColorGuardButton = new JButton("Remove Color Guard");
        removeColorGuardButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove from every set in field
            	for (int i = 0; i<field.getShow().getSets().size(); i++) {
            		// reference current set + remove marcher
            		Set setDummy = field.getShow().getSets().get(i);
            		if (f.getSelection() != null) {
            			if (f.getSelection().isColorguard()){ // make sure selection is colorguard
                			setDummy.removeMarcher(f.getSelection()); // Remove selected colorguard
                			// change set within show
                    		field.getShow().changeSet(i, setDummy);
                    		field.removeSelection();
                    		
                    		field.repaint();
                			
                		} else{
                			System.out.println("Must Select a Colorguard");
                		}
                		
            		} else {
            			System.out.println("Please make a selection :)");
            		}
            		
            		// change set within show
            		field.getShow().changeSet(i, setDummy);
            		
            		field.repaint();
            	}

            }


        });
        row2.add(removeColorGuardButton);
        controlPanel.add(row2);

        // play/stop buttons

        JPanel row3 = new JPanel(new FlowLayout());

        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                field.play();
            }
        });
        row3.add(playButton);

        JTextField tf = new JTextField("Enter Speed");
        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = tf.getText();
                field.changeSpeed(Integer.parseInt(input));
            }
        });
        row3.add(tf);

        controlPanel.add(row3);

        // set related buttons
        // display current set number
        displaySetNumLabel = new JLabel(getDisplaySetString()); // initialize the label
        controlPanel.add(displaySetNumLabel);

        JPanel row4 = new JPanel(new FlowLayout());

        JButton newSetButton = new JButton("New Set");
        newSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set currSet = field.getShow().getSets().get(setNum);
	            field.getShow().addSet(new Set(currSet.getTime(), currSet.getNumber(), currSet.getMarchers())); // If clone doesn't work, manually create a duplicate

                setNum = f.getShow().getSets().size() - 1;
                f.changeSet(setNum);
                field.repaint();
                updateSetNumLabel(); // update the set number display
            }
        });
        row4.add(newSetButton);

        JButton previousSetButton = new JButton("Previous Set");
        previousSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setNum >= 1) {
                    setNum--;
                    f.changeSet(setNum);
                    field.repaint();
                    updateSetNumLabel(); // update the set number display
                }
            }
        });
        row4.add(previousSetButton);

        JButton nextSetButton = new JButton("Next Set");
        nextSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setNum < field.getShow().getSets().size() - 1) {
                    setNum++;
                    f.changeSet(setNum);
                    field.repaint();
                    updateSetNumLabel(); // update the set number display
                }
            }
        });
        row4.add(nextSetButton);

        controlPanel.add(row4);

        add(controlPanel);
    }

    /**
     * Updates the set number label displayed on the GUI
     */
    private void updateSetNumLabel() {
        displaySetNumLabel.setText(getDisplaySetString());
    }

    /**
     * Returns the display string for the current set number.
     * 
     * @return The display string for the current set number.
     */
    private String getDisplaySetString() {
        return "You are on set: " + (setNum + 1) + "/" + field.getShow().getSets().size();
    }

    /**
     * Opens a new FieldEditor window. 
     */
    public void openEditor() {
        FieldEditor editor = new FieldEditor(field);
        editor.setVisible(true);
    }
}
