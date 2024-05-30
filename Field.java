import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The Field class initiates a show, initializes the window,
 * and paints the background footballField.png. It also 
 * draws the marchers in each set.
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
public class Field extends JPanel implements MouseListener{

    /**
     * The picture imported as the background football field
     */
    private Image pic;

    /**
     * Show associated with the current instance of 
     * field (window that is open)
     */
    private Show show;

    /**
     * The Jframe that is used to store the background 
     * picture and make it appear
     */
    private JFrame f;

    /**
     * The current set that the field is displaying at 
     * that instant
     */
    private int currentSetNum = 0;

    /**
     * The Marcher that has been clicked (Selected) 
     * by the user (for dragging)
     */
    private Marcher selection;

    /**
     * variables used to track and store user movement of mouse
     */
    private int initX, initY;

    /**
     * @see Marcher for marcherId
     * the marcherId of the Marcher that is selected
     */
    private int selectedMarcherId;

    /**
     * Variables that indicate the starting position of the mouse
     */
    private int pressStartX, pressStartY;

    /**
     * Sets the amount of frames in the animation of each set
     * to 50.
     */
    public static int FramesPerSet = 50;

    /**
     * Sets the default animation speed to 30.
     */
    public static int Speed = 30;

    /**
     * No args constructor for Field which creates a new background 
     * Jframe, and sets its picture to the football field. It also
     * begins to detect user mouse movement
     */
    public Field()
    {
        show = new Show();
        
        // Gets pic as toolkit img and converts to buffered
        pic = new HelperMethods().toBufferedImage((new ImageIcon(this.getClass().getResource("footballField.jpg"))).getImage());
        
        // Initialize Window
        f = new JFrame("MARCHING BAND SIMULATOR");
        f.setSize(720,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addMouseListener(this);
    }

    /**
     * Constructs a Field object with the specified Show
     * 
     * @param s The Show that would be displayed on the Field
     */
    public Field(Show s)
    {
        show = s;
        
        // Gets pic as toolkit img and converts to buffered
        pic = new HelperMethods().toBufferedImage((new ImageIcon(this.getClass().getResource("footballField.jpg"))).getImage());

    }
    
    /**
     * Changes the speed of the animation
     * 
     * @param newSpeed The new speed of animation
     */
    public static void changeSpeed(int newSpeed) 
    {
        Speed = newSpeed;
    }

    /**
     * Draws the football field image. 
     * 
     * @param g The Graphics object that does the drawing
     */
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.clearRect(0, 0, 720, 400);
        g.drawImage(pic, 0, 0, this);

        // calls function to draw marchers
        displaySet(g);
    }
    
    /**
     * Method called to make the Field window Visible to the user
     */
    public void openField()
    {
        f.add(this);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    

    /**
     * Iterates through each marcher in the current set, and
     * display them on the Field GUI
     */
    public void displaySet(Graphics g)
    {
        Set set = show.getSets().get(currentSetNum);

        // loop through and draw marchers
        for (int i = 0; i < set.getMarchers().size(); i++) 
        {
            Location loc = set.getMarchers().get(i).getLoc(currentSetNum);
            Image marcherPic; // just for initialization
            // different images for colorguard/horn
            if (set.getMarchers().get(i).isColorguard()) {
                marcherPic = (new ImageIcon(this.getClass().getResource("colorguardFlagUp.png")).getImage());
            }
            else {
                marcherPic = (new ImageIcon(this.getClass().getResource("marcherHornsUp.png")).getImage());
            }
            // subtract 10 because the image is 15 pixels to the bottom right
            g.drawImage(marcherPic, loc.getX()-15, loc.getY()-15, this);

            // indication rectangle thingy
            if (selection != null && selection.equals(set.getMarchers().get(i))) {
                g.setColor(Color.YELLOW);
                g.drawRect(loc.getX()-7, loc.getY()-10, 15, 25);
            }
        }
    }

    /**
     * Changes the current set number to the specified value
     * 
     * @param n New set that the field is displaying
     */
    public void changeSet(int n) { // Change set number
        currentSetNum = n;
        repaint();
    }
    
    /**
     * Method that animates the marchers moving from one set's location to the next
     */
    public synchronized void play()
    {
        new AnimationRunner(this); // Run animation
    }

    /**
     * Gets the Show currently being displayed on the Field
     * 
     * @return The field Show
     */
    public Show getShow()
    {
        return show;
    }

    /**
     * Gets the current set number
     * 
     * @return The current set number
     */
    public int currSet()
    {
        return currentSetNum;
    }

    /**
     * Gets the currently selected Marcher
     * 
     * @return The currently selected Marcher
     */
    public Marcher getSelection() {
        return selection;
    }
    
    /**
     * Removes the current Marcher selection
     */
    public void removeSelection() {
        selection = null;
    }

    /**
     * Detects the closest Marcher to the "click" 
     * 
     * @param e The MouseEvent that occurs
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        // Loop through all marchers and take the closest to click
        double min = 2000; // Set high initial value
        Marcher closest = null;

        for (int i =0; i<show.getSets().get(currentSetNum).getMarchers().size(); i++) {
            Marcher m = show.getSets().get(currentSetNum).getMarchers().get(i);
            double distance = Math.sqrt(Math.pow((x-m.getLoc(currentSetNum).getX()),2)+Math.pow((y-m.getLoc(currentSetNum).getY()),2)); // Pythagorean theorem
            if (distance < min){
                min = distance;
                closest = m;
            }
        }
        
        // If close enough, update selection
        if (min<12){
            selection = closest;
            repaint();
        }
    }

    /**
     * Detects the closest Marcher to select
     * 
     * @param e The mouseEvent that occured
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        pressStartX = x;
        pressStartY = y;

        // Loop through all marchers and take the closest to click
        double min = 2000; // Set high initial value
        int marcherId = -1;
        Marcher closest = null;

        for (int i =0; i<show.getSets().get(currentSetNum).getMarchers().size(); i++) {
            Marcher m = show.getSets().get(currentSetNum).getMarchers().get(i);
            double distance = Math.sqrt(Math.pow((x-m.getLoc(currentSetNum).getX()),2)+Math.pow((y-m.getLoc(currentSetNum).getY()),2)); // Pythagorean theorem
            if (distance < min){
                closest = m;
                min = distance;
                marcherId = i;
            } else {
                this.removeSelection();
            }
        }

        // If close enough, update selection and repaint
        if (min<12){
            selection = closest;

            Location loc = show.getSets().get(currentSetNum).getMarchers().get(marcherId).getLoc();

            initX = loc.getX();
            initY = loc.getY();
            selectedMarcherId = marcherId;

            repaint();
        }
    }

    /**
     * Updates the show's sets if the marcher was moved, and repaints the field
     * 
     * @param e The MouseEvent that occured
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (selection != null) {
            if (e.getX() != pressStartX || e.getY() != pressStartY){ //  ensure not a click
                Location loc = show.getSets().get(currentSetNum).getMarchers().get(selectedMarcherId).getLoc();

                int offsetX = e.getX() - initX; // Offset = current position - initial position
                int offsetY = e.getY() - initY;

                show.getSets().get(currentSetNum).getMarchers().get(selectedMarcherId).move(new Location(loc.getX()+offsetX, loc.getY()+offsetY), currentSetNum);; // Move marcher

                repaint();
            }
        }

    }

    /**
     * Unused method
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //dont use
    }

    /**
     * Unused method
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //dont use
    }
}
