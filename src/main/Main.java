package main;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(1000,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        gamePanel gamePanel = new gamePanel();
        window.add(gamePanel);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

    }
}