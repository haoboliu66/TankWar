package com.andy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame {

    public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.getInstance().getProperty("gameWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt(PropertyMgr.getInstance().getProperty("gameHeight"));

    GameModel gm = GameModel.getInstance();

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("TANK WAR");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        /**  close the game window */
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
        gm.paint(g);
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
                        gm.getMyTank().fire();
                        break;
                    default:
                        break;
                }
                setMainTankDir();
            }

            private void setMainTankDir(){
                Tank mytank = gm.getMyTank();
                if(!bL && !bU && !bR && !bD){
                    /**  all keys not pressed, set the tank not moving */
                    mytank.setMoving(false);
                    return;
                }
                /** either key is pressed */
                gm.getMyTank().setMoving(true);
                if(bL) mytank.setDir(Direction.LEFT);
                if(bU) mytank.setDir(Direction.UP);
                if(bR) mytank.setDir(Direction.RIGHT);
                if(bD) mytank.setDir(Direction.DOWN);
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
