package com.trapped.gui;

import com.trapped.player.Inventory;
import com.trapped.player.Player;
import com.trapped.utilities.Audio;
import com.trapped.utilities.Puzzle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;


public class MainWindow extends JFrame {

    // Font and styling
    public static final Font btnFont = new Font("Times New Roman", Font.BOLD, 10); // ORIGINAL
    public static final Font DISPLAY_AREA_FONT = new Font("Times New Roman", Font.ITALIC, 18); // ORIGINAL
    public static final int GUI_WIDTH = 1200;
    public static final int GUI_HEIGHT = 800;
    private static GamePanel gamePanel = null;

    //AUDIO
    private Audio audio = new Audio();


    /**
     * CONSTRUCTOR.
     */

    public MainWindow() {
        super("Trapped: Try to escape!"); //game window design

        //Initilize GamePanel

        gamePanel = new GamePanel(this);


        this.setSize(GUI_WIDTH, GUI_HEIGHT);//sets the x-dimension and y-dimension of the frame

        BackgroundImageLabelPanel backgroundPanel =
                new BackgroundImageLabelPanel(this);

        this.add(backgroundPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit out of application(default is hide application)
        this.setResizable(false); //prevent frame from being resized
        this.getContentPane().setBackground(new Color(100, 200, 200)); //set background collor

        GuiStartPanel guiStartPanel = new GuiStartPanel(this);
        setMainPanel(guiStartPanel);

        ImageIcon image = new ImageIcon("resources/images/trapped-image.png");
        this.setIconImage(image.getImage());

        //make frame visible
        this.setVisible(true);
    }


    public void setMainPanel(JPanel panel) {
        Container contentPane = this.getContentPane();
        //remove all existing components
        contentPane.removeAll();
        //add the new panel
        contentPane.add(panel);
        //refresh frame
        this.revalidate();
    }

    public void createGameScreen() {
        GuiIntroPanel panel = new GuiIntroPanel(this);
        setMainPanel(panel);
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }

    public Audio getAudio() {
        return audio;
    }

    /**
     * Reset the location and clear the inventory
     */
    public void restartGame() {
        //Reset the Location

        Player.location = "bed";
        Player player = Player.getInstance();

        //clear the inventory
        Inventory inventory = player.getInventory();
        inventory.getInvList().clear();

        //Reset the escape attempts
        Puzzle puzzle = Puzzle.getInstance();
        puzzle.setAttemptsLeft(3);

        createGameScreen();
    }
}