package com.trapped.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitPanel extends GuiPanel {

    public static final Font TITLE_FONT = new Font("Times New Roman", Font.BOLD, 50);
    public static final Font MESSAGE_FONT = new Font("Times New Roman", Font.BOLD, 30);

    /**
     * Constructor
     *
     * @param gui
     */
    public ExitPanel(MainWindow gui) {
        super(gui);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel btnPanel = createButtonPanel();

        JLabel titleLabel = new JLabel("Congratulations!!!!");
        titleLabel.setFont(TITLE_FONT);
        JLabel messageLabel = new JLabel("You Won!!!");
        messageLabel.setFont(MESSAGE_FONT);

        this.add(new JLabel(" "));
        this.add(titleLabel);
        this.add(new JLabel(" "));

        this.add(messageLabel);
        this.add(new JLabel(" "));

        this.add(btnPanel);
    }

    public static JPanel createButtonPanel() {
        //Restart panel design
        JPanel restartPanel = createRestartPanel();
        //exit panel design
        JPanel exitPanel = createExitPanel();

        JPanel btnPanel = new JPanel();

        btnPanel.add(restartPanel);
        btnPanel.add(exitPanel);
        return btnPanel;
    }


    private static JPanel createExitPanel() {
        JPanel exitPanel = new JPanel();
        exitPanel.setBounds(400, 400, 200, 300);
        exitPanel.setOpaque(false); //content pane opaque is false, has no effect

        Dimension dimension = new Dimension(400, 300);
        JButton exitButton = createExitPanel(dimension);
        exitPanel.add(exitButton);
        exitButton.setFocusPainted(false);

        return exitPanel;
    }


    static JButton createExitPanel(Dimension dimension) {
        JButton exitButton = new JButton(" EXIT ");

        exitButton.setSize(dimension);
        exitButton.setFont(MainWindow.btnFont);
        exitButton.setOpaque(false);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.RED);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainWindow, "Thank you for playing!!! Good BYe!!!");
                System.exit(0);
            }
        });
        return exitButton;
    }

    private static JPanel createRestartPanel() {
        JPanel restartPanel = new JPanel();
        restartPanel.setBounds(400, 400, 200, 300);
        restartPanel.setOpaque(false); //content pane opaque is false, has no effect

        Dimension dimension = new Dimension(400, 300);
        JButton restartButton = createRestartPanel(dimension);
        restartPanel.add(restartButton);
        restartButton.setFocusPainted(false);

        return restartPanel;
    }

    static JButton createRestartPanel(Dimension dimension) {
        JButton restartButton = new JButton(" RESTART ");

        restartButton.setSize(dimension);
        restartButton.setFont(MainWindow.btnFont);
        restartButton.setOpaque(false);
        restartButton.setForeground(Color.BLACK);
        restartButton.setBackground(Color.RED);

        //adding event listener
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RE-START Game Screen
                getMainWindow().restartGame();
            }
        });
        return restartButton;
    }

    private JButton createExitPanel(Dimension dimension) {
        JButton exitButton = new JButton(" EXIT ");
        exitButton.setSize(dimension);
        exitButton.setFont(MainWindow.btnFont);
        exitButton.setOpaque(false);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.CYAN);

        //adding exit event listener
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainWindow, "Thank you for playing!!! Good BYe!!!");
                System.exit(0);
            }
        });
        return exitButton;
    }
}
