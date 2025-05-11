import javax.swing.*;
import java.awt.*;
import java.util.Collections;
// jpanel with extra functions
public class gamePanel extends JPanel implements Runnable{
    // SCREEN settings
    final int originalTileSize = 16; // 16*16 tile typical character size
    final int scale = 3; // 16*16 frame so looks 48 by 48 on screen
    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16; // 16 tiles horizontally
    final int maxScreenRow = 12; // 12 tiles vertically
    final int screenWidth = tileSize * maxScreenColumn; // 48*16 = 768
    final int screenHeight = tileSize * maxScreenRow; //48*12 = 576

    Thread gameThread;
    keyHandler keyH = new keyHandler();


    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int FPS = 60;

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // default for better rendering,
        this.addKeyListener(keyH);
        this.setFocusable(true); //draws characters with less stutters

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run(){
        double drawInterval = 1000000000/FPS; // nano seconds used as calcualtion unit
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
            update();
            repaint();
            System.out.println("The game loop is running");
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                ;
                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // do nothing in the remaining time of game loop
                nextDrawTime +=drawInterval; //


            }catch (InterruptedException e) {
                e.printStackTrace();
            } {

            }


        }
    }
    /// update information such as character position

    public void update(){
        if(keyH.upPressed) playerY -=playerSpeed;
        else if(keyH.downPressed) playerY +=playerSpeed;
        else if(keyH.rightPressed) playerX += playerSpeed;
        else if(keyH.leftPressed) playerX -=playerSpeed;
    }

    ///  draw characters and screen

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize); // x, y, width, height
        g2.dispose();


    }

}



// how do 2d games work
// draws screen certain times
// so we use a thread to run a thread over and over again
// must implement the runnable interface
/// look over runnable interface
// creates a method in the runnable class called run