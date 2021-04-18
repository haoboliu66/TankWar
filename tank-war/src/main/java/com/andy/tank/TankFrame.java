package com.andy.tank;

import com.andy.loader.PropertyMgr;
import com.andy.tank.components.Direction;
import com.andy.tank.components.Tank;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class TankFrame extends Frame {

    public static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.getInstance().getProperty("gameWidth"));
    public static final int GAME_HEIGHT = Integer.parseInt(PropertyMgr.getInstance().getProperty("gameHeight"));

    GameModel gm;

    public TankFrame() {
        gm = GameModel.getInstance();

        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("TANK WAR");
        this.setVisible(true);

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


    /**
     * method automatically invoked when window is re-constructed
     */
    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        new ProcessBuilder("java", "Main").start();
                    } catch (IOException e) {
                    }
                }
            });
            mouseReleased(e);
            System.exit(0);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("release");
        }
    }

    private class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
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
            switch (key) {
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

        private void setMainTankDir() {
            Tank myTank = gm.getMyTank();
            if (!bL && !bU && !bR && !bD) {
                /**  all keys not pressed, set the tank not moving */
                myTank.setMoving(false);
                return;
            }
            /** either key is pressed */
            gm.getMyTank().setMoving(true);
            if (bL) myTank.setDir(Direction.LEFT);
            if (bU) myTank.setDir(Direction.UP);
            if (bR) myTank.setDir(Direction.RIGHT);
            if (bD) myTank.setDir(Direction.DOWN);
        }

    }


    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


}
