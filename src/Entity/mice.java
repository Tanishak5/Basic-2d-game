package Entity;

import main.gamePanel;
import main.keyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class mice extends entity{
    gamePanel gp;
    keyHandler keyH;
    BufferedImage up, down, right, left;

    public mice(gamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValue();
        drawMouse();

    }

    public void drawMouse(){
        try{
            right = ImageIO.read(getClass().getResourceAsStream("/Entity/res/right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Entity/res/left.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/Entity/res/up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Entity/res/down.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setDefaultValue(){
        x = 150;
        y = 150;
        speed = 4;
    }

    public void update()  {
        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                Random random = new Random();
                System.out.println("occurs");
                int randomNum = random.nextInt(4);
                if (randomNum == 0) {y -= speed; img = up;}
                else if (randomNum == 1) {y += speed; img = down;}
                else if (randomNum == 2) {x += speed; img = right;}
                else if (randomNum == 3) {x -= speed; img = left;}

            }
        };
        timer.scheduleAtFixedRate(task, 0, 10000);


    }


    public void draw(Graphics2D g2){
        if(x < gp.screenWidth && x >= 0 && y < gp.screenHeight & y >= 0){
            g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);
        }else if ( x < 0){
            g2.drawImage(img, 10, y, gp.tileSize, gp.tileSize, null);
        }
        else if ( y > gp.screenHeight){
            g2.drawImage(img, x, y-50, gp.tileSize, gp.tileSize, null);
        }
        else if ( y < 0){
            g2.drawImage(img, x, 10, gp.tileSize, gp.tileSize, null);
        }

    }


    }





