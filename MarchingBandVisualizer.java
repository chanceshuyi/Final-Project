import javax.swing.*;
import javafx.embed.swing.JFXPanel;

/**
 * This is the main class that the application runs from. 
 * It creates both GUI’s, creates a new show with one 
 * empty set, and allows the user to begin editing the field. 
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
public class MarchingBandVisualizer
{
    /**
     * The main method that runs the application. It opens the Field and Field Editor,
     * adds 20 marchers by default, and opens the sound panel.
     * 
     * @param args Command-line arguments

     */
    public static void main(String[] args)
    {
        //opens field
        Field field = new Field();
        field.openField();

        //adds 20 marchers
        for (int i = 1; i <= 20; i++) 
        {
            for (Set set : field.getShow().getSets()) 
            {
            	/*
            	 5 columns - spacing: 20
            	 4 rows - 
            	*/

                set.addMarcher(new HornMarcher(new Location(317 + ((i-1)/4)*20, 146+(i%4 * 20))));
                field.repaint();
            }
        }
                
        //opens editor
        FieldEditor editor = new FieldEditor(field);
        editor.openEditor();

        //opens sound panel
        new JFXPanel();
        SwingUtilities.invokeLater(SoundPlayer::new);
    }

}