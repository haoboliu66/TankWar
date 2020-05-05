package com.andy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    Tank myTank = new Tank(200,500,Direction.DOWN,this, Group.HERO);
    List<Bullet> bullets = new ArrayList<>(); // list for bullets
    List<Tank> enemies = new ArrayList<>(); // list for other tanks
    List<Explosion> explosions = new ArrayList<>(); // list for explosions

    public TankFrame(){

        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("TANK WAR");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {

            /** add windowClosing event */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /** method automatically invoked when window is re-constructed  */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("num of bullets: " + bullets.size(),10,60);
        g.drawString("num of enemies: " + enemies.size(),10,80);
        g.drawString("num of explosions: " + explosions.size(),10,100);
        g.setColor(c);

        myTank.paint(g);
        /** paint bullet  */
        for(int i=0; i<bullets.size(); i++){
            bullets.get(i).paint(g);
        }

        for(int i=0;i<enemies.size();i++){
            enemies.get(i).paint(g);
        }


        /** bullet and tank collision test */
        for(int i=0; i<bullets.size(); i++){
            for(int j=0; j<enemies.size(); j++){
                // collision test with enemies
                bullets.get(i).collideWith(enemies.get(j));


            }
        }

        /**  paint explosion */
        for(int i=0; i<explosions.size(); i++){
            explosions.get(i).paint(g);
        }


//        for(Iterator<Bullet> it = bullets.iterator(); it.hasNext()){
//            Bullet b = it.next();
//            if(!b.isLive()) it.remove();
//        }

    }

        class MyKeyListener extends KeyAdapter{
            boolean bL = false;
            boolean bU = false;
            boolean bR = false;
            boolean bD = false;

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_LEFT:
                        bL = true;
                        break;
                    case KeyEvent.VK_UP:
                        bU = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bR = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        bD = true;
                        break;
                    default:
                        break;
                }
                setMainTankDir();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_LEFT:
                        bL = false;
                        break;
                    case KeyEvent.VK_UP:
                        bU = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bR = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        bD = false;
                        break;
                    case KeyEvent.VK_CONTROL:
                        myTank.fire();
                        break;
                    default:
                        break;
                }
                setMainTankDir();
            }

            private void setMainTankDir(){
                if(!bL && !bU && !bR && !bD){
                    /**  all keys not pressed, set the tank not moving */
                    myTank.setMoving(false);
                    return;
                }
                /** either key is pressed */
                myTank.setMoving(true);
                if(bL) myTank.setDir(Direction.LEFT);
                if(bU) myTank.setDir(Direction.UP);
                if(bR) myTank.setDir(Direction.RIGHT);
                if(bD) myTank.setDir(Direction.DOWN);
            }

        }













    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }



}
