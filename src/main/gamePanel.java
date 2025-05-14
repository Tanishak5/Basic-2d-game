package main;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    player cat = new player(this, keyH);
    mice mouse = new mice(this, keyH);
    Timer timer;int second = 0, minute = 1;


    int FPS = 60;

    Set<Entity.mice> mice = createMice();
    public Set<mice> createMice(){
        Set<Entity.mice> temp = new HashSet<>();
        for(int i = 0; i < 30; i++){
            mice mouse = new mice(this, keyH);
            temp.add(mouse);

        }
        return temp;
    }
    Set<Integer> xPos = mice.stream().map(x -> x.getx()).collect(Collectors.toSet());
    Set<Integer> yPos = mice.stream().map(x -> x.gety()).collect(Collectors.toSet());
    music sound = new music();
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true); // default for better rendering,
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(null); //draws characters with less stutters
        timers();
        playMusic();

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public Boolean collision() {
        for(mice mouse: mice){
            if (mouse.getx() < (cat.getX() + 32) && mouse.getx() > (cat.getX() -32) && mouse.gety() < (cat.gety() + 32) && mouse.gety() > (cat.gety() - 32)){
                mice.remove(mouse);
                System.out.println("happens");
            }
        }
        return true;
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
                cat.update();
            }



    ///  draw characters and screen

            public void paintComponent (Graphics g){
                collision();
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                cat.draw(g2);
                for(mice mouse: mice){mouse.draw(g2);}

                String ddsecond, ddminute;
                DecimalFormat dformat = new DecimalFormat("00");
                ddsecond = dformat.format(second);
                ddminute = dformat.format(minute);
                String time = ddminute + ":" + ddsecond;
                g2.setFont(new Font("Arial", Font.BOLD, 30));
                g2.setColor(Color.RED);
                if(second ==0 && minute == 0){
                    g2.drawString("GAME OVER", 500,500);
                    gameThread = null;
                }
                g2.drawString(time, 800, 50);


                g2.dispose();


            }

            public void timers(){
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (second == -1 && minute == 0) {
                            timer.stop();

                        } else if (second == 0) {
                            second = 59;
                            minute--;
                        }else{second--;}

                    }
                });
                timer.start();
            }

            public void playMusic(){
                new Thread(() -> {
                    sound.setFile(0); // load WAV file
                    sound.play();
                    sound.loop();
                }).start();

            }

            public void stopMusic(){
                sound.stop();
            }

        }



