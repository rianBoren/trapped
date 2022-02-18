package com.trapped.gui;

import javax.swing.JPanel;

public class GuiPanel extends JPanel {

    public static MainWindow mainWindow;

    public GuiPanel(MainWindow mainWindow) {
        this.mainWindow=mainWindow;
    }
    protected static MainWindow getMainWindow() {
        return mainWindow;
    }
}
