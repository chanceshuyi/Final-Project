import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * The SoundPlayer allows users to upload and play music.
 * 
 * @author Chancie Chou
 * @version 5/23/2024
 * 
 * @author Period: 1
 * @author Assignment: Final Project
 * 
 * @author Sources - https://stackoverflow.com/questions/23202272/how-to-play-sounds-with-javafx
 */
public class SoundPlayer {
    /**
     * The media player for playing audio files
     */
    private MediaPlayer mediaPlayer;

    /**
     * The slider for adjusting the time of the music currently being played
     */
    private JSlider slider;

    /**
     * Indicates whether the slider is currently being slided
     */
    private boolean isSliding = false;

    /**
     * The queue of music files to be played
     */
    private Queue<File> musicQueue = new LinkedList<>();

    /**
     * A map to associate files with their corresponding buttons
     */
    private Map<File, JButton> fileButtonMap = new HashMap<>();

    /**
     * The currently selected file for playback or removal
     */
    private File selectedFile;

    /**
     * The panel for displaying the music queue
     */
    private JPanel queuePanel;

    /**
     * The frame that contains all the different SoundPlayer elements
     */
    private JFrame frame;

    /**
     * The label for displaying the currently playing song
     */
    private JLabel currentlyPlayingLabel;

    /**
     * Constructs a SoundPlayer object
     */
    public SoundPlayer() {
        frame = new JFrame("Sound Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new FlowLayout());

        JButton importButton = new JButton("Add to Queue");
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton removeButton = new JButton("Remove from Queue");

        slider = new JSlider(0, 100, 0);
        slider.setPreferredSize(new Dimension(500, 40));
        slider.setEnabled(false);

        queuePanel = new JPanel();
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.Y_AXIS));
        queuePanel.setPreferredSize(new Dimension(500, 200));
        queuePanel.setBorder(BorderFactory.createTitledBorder("Queue"));

        currentlyPlayingLabel = new JLabel("Currently playing: None");
        currentlyPlayingLabel.setPreferredSize(new Dimension(500, 20));

        importButton.addActionListener(this::addToQueue);
        playButton.addActionListener(this::playSound);
        pauseButton.addActionListener(this::pauseSound);
        removeButton.addActionListener(this::removeFromQueue);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isSliding && slider.getValueIsAdjusting() && mediaPlayer != null) {
                    Platform.runLater(() -> {
                        double duration = mediaPlayer.getTotalDuration().toSeconds();
                        mediaPlayer.seek(javafx.util.Duration.seconds(duration * slider.getValue() / 100));
                    });
                }
            }
        });

        frame.add(importButton);
        frame.add(playButton);
        frame.add(pauseButton);
        frame.add(removeButton);
        frame.add(slider);
        frame.add(queuePanel);
        frame.add(currentlyPlayingLabel);

        frame.setLocation(600, 0);
        frame.setVisible(true);
    }

    /**
     * Adds a music file to the queue
     * 
     * @param e The action event triggered by the user
     */
    private void addToQueue(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Sound File");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (musicQueue.size() < 4) {
                musicQueue.add(file);
                JButton fileButton = new JButton(file.getName());
                fileButton.addActionListener(evt -> selectFile(file));
                fileButtonMap.put(file, fileButton);
                queuePanel.add(fileButton);
                queuePanel.revalidate();
                queuePanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Queue is full. Remove a song to add more.");
            }
        }
    }

    /**
     * Selects a file for playback or removal
     * 
     * @param file The file to be selected
     */
    private void selectFile(File file) {
        selectedFile = file;
        for (Map.Entry<File, JButton> entry : fileButtonMap.entrySet()) {
            JButton button = entry.getValue();
            if (entry.getKey().equals(file)) {
                button.setBackground(Color.LIGHT_GRAY);  // Highlight selected button
            } else {
                button.setBackground(null);  // Reset other buttons' background
            }
        }
    }

    /**
     * Removes a music file from the queue
     * 
     * @param e The action event triggered by the user
     */
    private void removeFromQueue(ActionEvent e) {
        if (selectedFile != null) {
            musicQueue.remove(selectedFile);
            JButton fileButton = fileButtonMap.remove(selectedFile);
            queuePanel.remove(fileButton);
            queuePanel.revalidate();
            queuePanel.repaint();
            selectedFile = null;  // Reset selected file after removal
        } else {
            JOptionPane.showMessageDialog(null, "Please select a file to remove.");
        }
    }

    /**
     * Plays the currently selected music file or the first in the queue if none is selected
     * 
     * @param e The action event triggered by the user
     */
    private void playSound(ActionEvent e) {
        if (selectedFile == null && !musicQueue.isEmpty()) {
            selectedFile = musicQueue.peek(); // Default to the first in queue if none selected
        }

        if (selectedFile != null) {
            if (mediaPlayer != null) {
                Platform.runLater(() -> mediaPlayer.stop());
            }
            playSpecificTrack(selectedFile);
        } else {
            JOptionPane.showMessageDialog(null, "Queue is empty.");
        }
    }

    /**
     * Pauses the currently playing music
     * 
     * @param e  The action event triggered by the user
     */
    private void pauseSound(ActionEvent e) {
        if (mediaPlayer != null) {
            Platform.runLater(() -> mediaPlayer.pause());
        }
    }

    /**
     * Plays a specific music file from the queue
     * 
     * @param file  The file to be played
     */
    private void playSpecificTrack(File file) {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.dispose();
            }
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setOnReady(() -> {
                slider.setEnabled(true);
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (!isSliding) {
                        double currentTime = newTime.toSeconds();
                        double duration = mediaPlayer.getTotalDuration().toSeconds();
                        slider.setValue((int) ((currentTime / duration) * 100));
                    }
                });
                mediaPlayer.play();
                updateCurrentlyPlayingLabel(file);
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                Platform.runLater(() -> {
                    slider.setValue(0);
                    musicQueue.poll(); // Remove the played file from the queue
                    playNextInQueue();
                });
            });
        });
    }

    /**
     * Plays the next music file in the queue
     */
    private void playNextInQueue() {
        if (!musicQueue.isEmpty()) {
            File file = musicQueue.peek(); // Get the file at the head of the queue without removing it
            playSpecificTrack(file);
        } else {
            Platform.runLater(() -> {
                JOptionPane.showMessageDialog(null, "Queue is empty.");
                currentlyPlayingLabel.setText("Currently playing: None");
                slider.setEnabled(false);
            });
        }
    }

    /**
     * Updates the label to display the currently playing song
     * 
     * @param file The file currently being played
     */
    private void updateCurrentlyPlayingLabel(File file) {
        currentlyPlayingLabel.setText("Currently playing: " + file.getName());
    }

    /**
     * The main method that launches the SoundPlayer application
     * 
     * @param args The command line arguments
     */
    public void test() 
    {
        new JFXPanel();
        SwingUtilities.invokeLater(SoundPlayer::new);
    }
}
