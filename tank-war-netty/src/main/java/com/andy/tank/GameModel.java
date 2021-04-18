package com.andy.tank;

import com.andy.collidor.CollidorChain;
import com.andy.loader.PropertyMgr;
import com.andy.loader.ResourceMgr;
import com.andy.tank.components.*;
import com.andy.utils.DirectionUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andy-liu
 * @date 2020/5/5 - 5:17 PM
 */

/**
 * GameModel acts as Facade
 */
public class GameModel {

    private static GameModel GM_INSTANCE = new GameModel();

    static {
        GM_INSTANCE.init();
    }

    Tank myTank;
    private static List<Tank> numberOfEnemies;

    private List<GameObject> objects = new ArrayList<>();
    CollidorChain chain = new CollidorChain();

    private GameModel() {
    }

    public void init() {
        myTank = new Tank(100, 600, Direction.DOWN, Group.HERO);
        numberOfEnemies = new ArrayList<>();
        initializeTanks();
        createWall();
    }


    private void initializeTanks() {
        int initTankCount = Integer.parseInt(PropertyMgr.getInstance().getProperty("initTankCount"));
        //initialize enemies tank
        for (int i = 0; i < initTankCount; i++) {
            int x = 50 + i * 150;
            int y = (i + 2) * 50;
            if (x > TankFrame.GAME_WIDTH) {
                x = x % TankFrame.GAME_WIDTH;
            }
            if (y > TankFrame.GAME_HEIGHT) {
                y = y % TankFrame.GAME_HEIGHT;
            }
            numberOfEnemies.add(new Tank(x, y, DirectionUtils.getRandomDirection(), Group.VILLAIN));
        }
    }

    private void createWall() {
        for (int j = 0; j < 50; j++) {
            for (int i = 0; i < (int) (Math.random() * 10); i++) {
                new Wall(300 + i * Wall.WIDTH, 400);
            }

            for (int i = 0; i < 6; i++) {
                new Wall(800 + i * Wall.WIDTH, 800);
            }

            for (int i = 0; i < 6; i++) {
                new Wall(1100, 500 + i * Wall.HEIGHT);
            }
        }

    }


    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        //打印行为
//        System.out.println(numberOfEnemies);

        if (numberOfEnemies.size() == 0) { // youWin
            BufferedImage youWin = ResourceMgr.gameOver;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.drawImage(ResourceMgr.youWin, TankFrame.GAME_WIDTH / 2 - youWin.getWidth() / 2, TankFrame.GAME_HEIGHT / 2 - youWin.getHeight() / 2, null);
            return;
        }
        if (!myTank.isLive()) { // GameOver
            BufferedImage gameOver = ResourceMgr.gameOver;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.drawImage(ResourceMgr.gameOver, TankFrame.GAME_WIDTH / 2 - gameOver.getWidth() / 2, TankFrame.GAME_HEIGHT / 2 - gameOver.getHeight() / 2, null);
            return;
        }

        /** paint objects  */
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        /** collision test   */
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                chain.collideWith(objects.get(i), objects.get(j));
            }
        }
    }


    public Tank getMyTank() {
        return myTank;
    }

    public static GameModel getInstance() {
        return GM_INSTANCE;
    }

    public static void setNumberOfEnemies(Tank tank) {
        numberOfEnemies.remove(tank);
    }
}
