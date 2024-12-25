package org.graphic;

import main.Main;

import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Display {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Shoot 'em all!");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(false);
        mainFrame.add(new Main(mainFrame));
        mainFrame.setVisible(true);
    }

}
