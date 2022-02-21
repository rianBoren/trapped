package com.trapped.gui;

import com.trapped.utilities.Puzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class KeypadPanel extends GuiPanel {

    private Puzzle puzzle = Puzzle.getInstance();
    //
    private String currentDigits = "";
    private String result = "";
    private JLabel display;
    private static final String trys = "3 trys";
    public static final Font BTN_FONT = new Font("SansSerif", Font.BOLD, 10); // ORIGINAL
    //
    private static final int BOX_WIDTH = 360;
    private static final int BOX_HEIGHT = 400;
    private static final int KP_WIDTH = 340;
    private static final int KP_HEIGHT = 380;
    private static final int BTNS_HEIGHT = 320;
    private static final int DISPLAY_HEIGHT = 60;

    public KeypadPanel(MainWindow mainWindow) {
        super(mainWindow);

        this.setBackground(new Color(125, 115, 115));
        this.setBounds(400, 0, BOX_WIDTH, BOX_HEIGHT);
        this.setLayout(null);

        JPanel keypadPanel = new JPanel();
        keypadPanel.setBackground(new Color(0, 26, 77));
        keypadPanel.setBounds(10, 10, KP_WIDTH, KP_HEIGHT);
        keypadPanel.setLayout(null);
        keypadPanel.add(createDisplayPanel());
        keypadPanel.add(createKeypadPanel());

        this.add(keypadPanel);
    }

    private JPanel createDisplayPanel() {
        // Make Panel
        JPanel screen = new JPanel();
        screen.setBackground(Color.black);
        screen.setBounds(0, 0, KP_WIDTH, DISPLAY_HEIGHT);

        // Set and add digit display to panel
        display = new JLabel();
        display.setText(trys);
        display.setForeground(new Color(53, 255, 31));
        display.setFont(new Font("Monospaced", Font.PLAIN, 50));
        //
        screen.add(display);

        return screen;
    }

    private JPanel createKeypadPanel() {
        JPanel keypad = new JPanel();
        keypad.setBackground(new Color(160, 160, 160));
        keypad.setLayout(new GridLayout(4, 3, 10, 10));
        keypad.setBounds(0, DISPLAY_HEIGHT, KP_WIDTH, BTNS_HEIGHT);

        // Make buttons
        JButton one_btn = new JButton("1");
        JButton two_btn = new JButton("2");
        JButton three_btn = new JButton("3");
        JButton four_btn = new JButton("4");
        JButton five_btn = new JButton("5");
        JButton six_btn = new JButton("6");
        JButton seven_btn = new JButton("7");
        JButton eight_btn = new JButton("8");
        JButton nine_btn = new JButton("9");
        JButton zero_btn = new JButton("0");
        JButton back_btn = new JButton("<<");
        // btn font
        one_btn.setFont(BTN_FONT);
        two_btn.setFont(BTN_FONT);
        three_btn.setFont(BTN_FONT);
        four_btn.setFont(BTN_FONT);
        five_btn.setFont(BTN_FONT);
        six_btn.setFont(BTN_FONT);
        seven_btn.setFont(BTN_FONT);
        eight_btn.setFont(BTN_FONT);
        nine_btn.setFont(BTN_FONT);
        zero_btn.setFont(BTN_FONT);
        back_btn.setFont(BTN_FONT);
        // Add action listeners
        one_btn.addActionListener(new KeypadListener("1"));
        two_btn.addActionListener(new KeypadListener("2"));
        three_btn.addActionListener(new KeypadListener("3"));
        four_btn.addActionListener(new KeypadListener("4"));
        five_btn.addActionListener(new KeypadListener("5"));
        six_btn.addActionListener(new KeypadListener("6"));
        seven_btn.addActionListener(new KeypadListener("7"));
        eight_btn.addActionListener(new KeypadListener("8"));
        nine_btn.addActionListener(new KeypadListener("9"));
        zero_btn.addActionListener(new KeypadListener("0"));
        back_btn.addActionListener(action -> {
            if(currentDigits.length() > 0){
                StringBuilder removing = new StringBuilder(currentDigits);
                removing.deleteCharAt(currentDigits.length() - 1);
                currentDigits = removing.toString();
                System.out.println("variable" + currentDigits);
                System.out.println("length: " +currentDigits.length());
            }
            displayRefresh();
        });
        // Add btns to panel
        keypad.add(one_btn);
        keypad.add(two_btn);
        keypad.add(three_btn);
        keypad.add(four_btn);
        keypad.add(five_btn);
        keypad.add(six_btn);
        keypad.add(seven_btn);
        keypad.add(eight_btn);
        keypad.add(nine_btn);
        keypad.add(zero_btn);
        keypad.add(back_btn);

        return keypad;
    }

    private class KeypadListener implements ActionListener {
        String value = "";

        public KeypadListener(String digit) {
            value = digit;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentDigits += value;
            displayRefresh();
        }
    }

    private void displayRefresh() {
        if (currentDigits.length() == 0){
            if (result.length() == 0){
                display.setText(trys);
            } else {
                display.setText(result);
            }
        }
        if (currentDigits.length() < 3) {
            display.setText(currentDigits);
        } else {
            result = puzzle.finalPuzzle(currentDigits);
            display.setText(result);
            currentDigits = "";
        }

        if ("Success".equals(result)) {
            JOptionPane.showMessageDialog(this, "You've unlocked the door!!!");
            GamePanel.createGameOverScreen("The door creaks open and you peek outside..." +
                    "   === Look out for the sequal, \"Trapped: Outside\" ===", "/image/gameover.png");
        }

//        if (attemptsLeft == 0) {
        if ("0 left".equals(result)) {
            JOptionPane.showMessageDialog(this, "The door keypad shuts off and the door remains closed. You've lost.");
            GamePanel.createGameOverScreen("The door is locked forever... Try again?", "/image/gameover.png");
        }
    }
}