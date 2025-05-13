package main;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

import Entity.player;
import Entity.mice;


// jpanel with extra functions
public class gamePanel extends JPanel implements Runnable{
    // SCREEN settings
    final int originalTileSize = 16; // 16*16 tile typical character size
    final int scale = 3; // 16*16 frame so looks 48 by 48 on screen
    public int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16; // 16 tiles horizontally
    final int maxScreenRow = 12; // 12 tiles vertically
    public int screenWidth = tileSize * maxScreenColumn; // 48*16 = 768
    public int screenHeight = tileSize * maxScreenRow; //48*12 = 576

    Thread gameThread;
    keyHandler keyH = new keyHandler();
    player player = new player(this, keyH);
    mice mice = new mice(this, keyH);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int FPS = 60;

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true); // default for better rendering,
        this.addKeyListener(keyH);
        this.setFocusable(true); //draws characters with less stutters

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run(){
        double drawInterval = 1000000000.0 / FPS; // in nanoseconds how often called
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000.0; // convert to ms

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime); // do nothing in the remaining time
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}}


            /// update information such as character position

            public void update () {
                player.update();
                mice.update();

            }

            ///  draw characters and screen

            public void paintComponent (Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                player.draw(g2);
                mice.draw(g2);

                g2.dispose();


            }

        }



