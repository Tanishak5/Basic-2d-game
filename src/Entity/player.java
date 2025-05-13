package Entity;
import main.gamePanel;
import main.keyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class player extends entity{
    gamePanel gp;
    keyHandler keyH;

    public player(gamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValue();
        getPlayerImage();


    }

    public void getPlayerImage(){
        try{
            img = ImageIO.read(getClass().getResourceAsStream("/Entity/res/img.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }



    public void setDefaultValue(){
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update(){
        if(keyH.upPressed) y -=speed;
        else if(keyH.downPressed) y +=speed;
        else if(keyH.rightPressed) x += speed;
        else if(keyH.leftPressed) x -=speed;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);

    }

}
