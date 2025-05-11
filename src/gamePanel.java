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

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // default for better rendering,
                                      //draws characters with less stutters

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        while(gameThread != null){
            System.out.println("The game loop is running");


            /// update information such as character position
            public void update(){}

            ///  draw characters and screen
            public void paintComponent(){

            }
            /// down key means we add 3 to y coordinates
            ///  up key means we add 3 to x coordinates
        }

    }

}

// how do 2d games work
// draws screen certain times
// so we use a thread to run a thread over and over again
// must implement the runnable interface
/// look over runnable interface
// creates a method in the runnable class called run