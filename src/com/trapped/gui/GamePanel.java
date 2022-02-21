package com.trapped.gui;

import com.gui.utility.GuiUtil;
import com.trapped.player.Inventory;
import com.trapped.player.Player;
import com.trapped.utilities.Audio;
import com.trapped.utilities.Puzzle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends GuiPanel {

    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20); // ORIGINAL
    private static final Color BTN_CLR = new Color(145,66,13);
    private static final Color BTN_FONT_CLR = Color.white;
    private static final Color PANELING_CLR = new Color(78, 72, 68);

    private static final int IMAGE_WIDTH = 800;
    private static final int IMAGE_HEIGHT = 400;

    private static final int ITEM_IMAGE_WIDTH = 50;
    private static final int ITEM_IMAGE_HEIGHT = 50;

    private static final int TIME = 480;

    static JLayeredPane layeredPane;
    private JPanel imagePanel;
    private JPanel textPanel;
    private JPanel buttonsPanel;
    private JPanel inventoryPanel;
    private JPanel keypadPanel;
    private JTextArea textArea;
    private JPanel instructionsPanel;
    private JLabel imageLabel;
    private JLabel timer;
    private JButton leftB;
    private JButton rightB;

    Player player = Player.getInstance();


    /**
     * Constructor.
     *
     * @param mainWindow
     */
    public GamePanel(MainWindow mainWindow) {
        super(mainWindow);

        this.setLayout(null);
        this.setSize(MainWindow.GUI_WIDTH, MainWindow.GUI_HEIGHT);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setSize(MainWindow.GUI_WIDTH, MainWindow.GUI_HEIGHT);
        this.add(layeredPane);
        createPanel();
        setLocationDetails();
    }

    private void setLocationDetails() {

        // set location description
        Map<String, Object> currentLoc = Puzzle.MAP.get(player.getLocation());
        String desc = (String) currentLoc.get("furniture_desc");
        textArea.setText("Current Location: " + player.getLocation());
        textArea.append("\n\n");
        textArea.append(desc);

        // set location image
        setLocationImage();
    }

    private void createPanel() {
        createKeypadPanel();
        createKeypadInstructionsPanel();
        createTextPanel();
        createImagePanel();
        createButtonsPanel();
        createInventoryPanel();
    }


    public void createCountdownPanel() {
        timer = new JLabel();
        timer.setBounds(200, 0, 200, 200);
        timer.setForeground(Color.black);
        timer.setVisible(true);
        startTimer(TIME);
      
        buttonsPanel.add(timer);
    }

    private void startTimer(int time) {
        java.util.Timer count = new Timer();

        count.scheduleAtFixedRate(new TimerTask() {
            int countdown = time;

            @Override
            public void run() {
                int secs = countdown % 60;
                int min = Math.floorDiv(countdown, 60);
                timer.setText("Time left: " + min + ":" + (secs < 10 ? "0" + secs : secs));
                --countdown;

                if (countdown < 0) {
                    count.cancel();
                    timer.setText("TIME OVER");
                    createGameOverScreen("You ran out of time! Now you're dead.", "/image/gameover.png");
                }
            }
        }, 0, 1000);
    }

    private void createTextPanel() {
        Color txtPanelClr = new Color(55,51,49);

        textPanel = new JPanel();
        textPanel.setBounds(0, 0, 400, 400);
        textPanel.setBackground(txtPanelClr);

        textArea = new JTextArea();
        textArea.setBounds(textPanel.getBounds());
        textArea.setFont(MainWindow.DISPLAY_AREA_FONT);
        textArea.setBackground(txtPanelClr);
        textArea.setForeground(Color.white);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textPanel.add(textArea);
        layeredPane.add(textPanel);
    }

    private void createImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setBounds(400, 0, 800, 400);
        layeredPane.add(imagePanel);
    }

    private void setLocationImage() {
        String filePath = "/image/" + Player.getLocation() + ".jpg";
        imageLabel = GuiUtil.getImageLabel(filePath, IMAGE_WIDTH, IMAGE_HEIGHT);

        //Set bounds (x, y, width, height) of the image same as that of the imagePanel
        imageLabel.setBounds(imagePanel.getBounds());
        //remove old image
        imagePanel.removeAll();
        //add new image
        imagePanel.add(imageLabel);
    }

    private void createButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, 400, 400, 400);
        buttonsPanel.setBackground(PANELING_CLR);

        leftB = new JButton("Left");
        leftB.setFont(BUTTON_FONT);
        leftB.setBackground(BTN_CLR);
        leftB.setForeground(BTN_FONT_CLR);
        rightB = new JButton("Right");
        rightB.setFont(BUTTON_FONT);
        rightB.setBackground(BTN_CLR);
        rightB.setForeground(BTN_FONT_CLR);

        JButton inspectB = new JButton("Inspect");
        inspectB.setFont(BUTTON_FONT);
        inspectB.setBackground(BTN_CLR);
        inspectB.setForeground(BTN_FONT_CLR);

//        JButton helpB = new JButton("Help");
//        helpB.setFont(BUTTON_FONT);

        JButton quitB = new JButton("Quit");
        quitB.setFont(BUTTON_FONT);
        quitB.setBackground(BTN_CLR);
        quitB.setForeground(BTN_FONT_CLR);

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
        p1.add(leftB);
        p1.add(new JLabel("     "));
        p1.add(rightB);
        p1.setBackground(PANELING_CLR);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        p2.add(inspectB);
        p2.setBackground(PANELING_CLR);
//        p2.add(new JLabel("     "));
//        p2.add(helpB);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
        p3.add(quitB);
        p3.setBackground(PANELING_CLR);

        JPanel panelY = new JPanel();
        panelY.setLayout(new BoxLayout(panelY, BoxLayout.Y_AXIS));
        panelY.setBackground(PANELING_CLR);

        //add space at the top of buttons
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));
        panelY.add(p1);
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));

        panelY.add(p2);
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));

        panelY.add(p3);
        panelY.add(new JLabel(" "));

        VolumeSliderPanel volumeSliderPanel = new VolumeSliderPanel(mainWindow, Audio.DEFAULT_VOLUME_LEVEL);
        volumeSliderPanel.setBackground(PANELING_CLR);
        volumeSliderPanel.setForeground(BTN_FONT_CLR);
        volumeSliderPanel.setFont(BUTTON_FONT);
        panelY.add(volumeSliderPanel);


        //Set bounds (x, y, width, height) of the panelY same as that of the buttonsPanel
        panelY.setBounds(buttonsPanel.getBounds());
        buttonsPanel.add(panelY);

        leftB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.moveDirection("left");
                setLocationDetails();
            }
        });

        rightB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.moveDirection("right");
                setLocationDetails();
            }
        });
        inspectB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inspectLocation();
                if ("door".equals(player.getLocation())) {
                    // show keypad and instructions
                    keypadPanel.setVisible(true);
                    instructionsPanel.setVisible(true);
                    // disable left & right
                    leftB.setEnabled(false);
                    rightB.setEnabled(false);
                } else {
                    player.solvePuzzle(player.getLocation());
                    displayInventoryDetails();
                }
            }
        });

        quitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainWindow, "Good Bye!");
                System.exit(0);
            }
        });

        layeredPane.add(buttonsPanel);
    }

    // create keypad for door exit
    private void createKeypadPanel() {
        keypadPanel = new KeypadPanel(mainWindow);
        keypadPanel.setVisible(false);  // hide panel
        layeredPane.add(keypadPanel);
    }

    // create instructions to the right of keypad
    private void createKeypadInstructionsPanel() {
        // create and place panel
        instructionsPanel = new GuiPanel(mainWindow);
        instructionsPanel.setBackground(Color.darkGray);
        instructionsPanel.setBounds(760, 0, 440, 400);
        instructionsPanel.setLayout(new BorderLayout());

        // create and add instructions label
        JTextArea instructions = new JTextArea("There's no going back from here\n" +
                "Use the \"<<\" button to delete\n\nOnly 3 attempts to escape\n" +
                "Input three digits if you dare\n\n***You notice some strange \nscrawling below the instructions \nthat you can't quite make out***");
        instructions.setPreferredSize(new Dimension(440,370));
        instructions.setBackground(Color.black);
        instructions.setFont(new Font("Monospaced", Font.PLAIN, 20));
        instructions.setForeground(new Color(247,253,255));

        instructionsPanel.add(instructions, BorderLayout.NORTH);

        // exit btn, hides the Keypad and Instructions
        // activates left & right btns
        JButton exit_btn = new JButton("exit");
        exit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keypadPanel.setVisible(false);
                instructionsPanel.setVisible(false);
                leftB.setEnabled(true);
                rightB.setEnabled(true);
            }
        });
        instructionsPanel.add(exit_btn);

        instructionsPanel.setVisible(false); // hide
        layeredPane.add(instructionsPanel);
    }

    private void inspectLocation() {
        List<String> items = JsonMap.getFurnitureItems(player.getLocation());
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(mainWindow, "No items found at: " + player.getLocation());
            return;
        }

        String item = items.get(0);

        Inventory inventory = player.getInventory();
        if (inventory.hasItem(item)) {
            JOptionPane.showMessageDialog(mainWindow, "You already have item: " + item + " from " + player.getLocation());
            return;
        }

        int response = JOptionPane.showConfirmDialog(mainWindow, "Item " + item +
                " found in '" + player.getLocation() + "'. Do you want to add it to your inventory?", "CONFIRM", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            return;
        }
        player.getInventory().addItem(item);
    }

    private JButton createButtonImage(String item) {
        String filePath = "/image/items/" + item + ".jpg";
//        JLabel imageLabel = GuiUtil.getImageLabel(item, filePath, ITEM_IMAGE_WIDTH, ITEM_IMAGE_HEIGHT, SwingConstants.TOP);
        JButton btn = GuiUtil.getButtonImage(filePath, ITEM_IMAGE_WIDTH, ITEM_IMAGE_HEIGHT);
        btn.setText(item);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void createInventoryPanel() {
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(400, 400, 800, 400);
        inventoryPanel.setBackground(Color.white);

        JLabel label = new JLabel("Inventory List:");
        label.setBounds(inventoryPanel.getBounds());
        inventoryPanel.add(label);
        layeredPane.add(inventoryPanel);
    }

    public void displayInventoryDetails() {
        List<String> invList = player.getInventory().getInvList();
        if ((invList == null) || (invList.isEmpty())) {
            return;
        }

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.X_AXIS));
        itemsPanel.setBackground(Color.white);

        //Add image labels
        for (String inventoryItem : invList) {
            itemsPanel.add(new JLabel("      "));
            JPanel p;
            if(inventoryItem.equals("a piece of paper with number 104")) {
                p = createRandomPuzzlePanel("paper"); //must match file name of 'paper.jpg'
            } else {
                p = createItemImagePanel(inventoryItem);
            }
            itemsPanel.add(p);
        }

        JPanel panelY = new JPanel();
        panelY.setLayout(new BoxLayout(panelY, BoxLayout.Y_AXIS));
        panelY.setBackground(Color.white);

        //add space at the top of buttons
        panelY.add(new JLabel(" "));

        //add title label
        JLabel titleLabel = new JLabel("Inventory List:");
        panelY.add(titleLabel);
        panelY.add(new JLabel("  "));
        panelY.add(new JLabel("  "));
        panelY.add(new JLabel("  "));

        //add image panel
        panelY.add(itemsPanel);
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));
        panelY.add(new JLabel(" "));

        //Set bounds (x, y, width, height) of panelY same as that of the inventoryPanel
        panelY.setBounds(inventoryPanel.getBounds());

        //Clear OLD Inventory images
        inventoryPanel.removeAll();
        //Add NEW Inventory images
        inventoryPanel.add(panelY);

        //refresh main window
        mainWindow.revalidate();
    }

    private JPanel createItemImagePanel(String inventoryItem) {
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JButton btn = createButtonImage(inventoryItem);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> result = player.solveUseTool(inventoryItem);
                String desc = result.get("puzzleDescription");
                String error = result.get("error");
                textArea.setText(error != null ? error : desc);
                displayInventoryDetails();
            }
        });
        p.add(btn);
        p.add(new JLabel("    "));
        return p;
    }


    private JPanel createRandomPuzzlePanel(String inventoryItem) {

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setSize(new Dimension(75, 75));

        //background label design
        JLabel backgroundImageLabel = new JLabel();
        backgroundImageLabel.setSize(backgroundPanel.getWidth(), backgroundPanel.getHeight());

        //setting background image
        String filePath = "/image/items/" + inventoryItem + ".jpg";
        URL imageURL = BackgroundImageLabelPanel.class.getResource(filePath);
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon(imageURL).getImage()
                .getScaledInstance(backgroundImageLabel.getWidth(), backgroundImageLabel.getHeight(), Image.SCALE_SMOOTH));
        backgroundImageLabel.setIcon(backgroundImage);
        backgroundPanel.add(backgroundImageLabel);

        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        String passcode = Puzzle.generateRandomPasscode(3);

        backgroundImageLabel.removeAll();
        backgroundImageLabel.setLayout(new GridBagLayout());
        backgroundImageLabel.add(new JLabel(passcode));

        backgroundPanel.add(new JLabel("   "));
        backgroundPanel.add(new JLabel("Paper with Passcode"));

        return backgroundPanel;
    }

    static void createGameOverScreen(String reason, String file) {
        JPanel gameOver = new JPanel();
        gameOver.setBounds(layeredPane.getBounds());
        gameOver.setBackground(Color.black);

        JLabel gameOverImage = GuiUtil.getImageLabel(file, 700, 450);
//        JTextArea textArea = new JTextArea();
//        textArea.setText(reason);
//        textArea.setFont(new Font("Helvetica", Font.BOLD, 20));
//        textArea.setForeground(Color.white);
//        textArea.setBackground(Color.black);
//        textArea.setWrapStyleWord(true);
//        textArea.setEditable(false);
//        textArea.setBounds(0,450, 1200, 350);
        gameOverImage.setText(reason);
        gameOverImage.setForeground(Color.white);
        gameOverImage.setIconTextGap(30);
        gameOverImage.setFont(new Font("Helvetica", Font.BOLD, 15));
        gameOverImage.setVerticalTextPosition(JLabel.BOTTOM);
        gameOverImage.setHorizontalTextPosition(JLabel.CENTER);

        JButton restart = ExitPanel.createRestartPanel(new Dimension(200, 200));
        JButton exit = ExitPanel.createExitPanel(new Dimension(200, 200));
        restart.setForeground(Color.white);
        restart.setFocusPainted(false);
        exit.setForeground(Color.white);


        gameOver.add(gameOverImage);
        gameOver.add(restart);
        gameOver.add(exit);


//        gameOver.add(textArea);
        layeredPane.removeAll();
        layeredPane.add(gameOver);
        layeredPane.moveToFront(gameOver);
    }
}