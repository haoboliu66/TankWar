package com.andy.tank;

import com.andy.collidor.CollidorChain;
import com.andy.decorator.TankDecorator;
import utils.Utils;

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
    private static int numberOfEnemies;

    TankDecorator tankDecorator;

    private List<GameObject> objects = new ArrayList<>();
    CollidorChain chain = new CollidorChain();

    private GameModel() {
    }

    public void init() {
        myTank = new Tank(100, 600, Direction.DOWN, Group.HERO);
//        tankDecorator = new TankDecorator(myTank);
        numberOfEnemies = initializeTanks();
        createWall();
    }


    private int initializeTanks() {
        int initTankCount = Integer.parseInt(PropertyMgr.getInstance().getProperty("initTankCount"));
        //initialize enemies tank
        for (int i = 0; i < initTankCount; i++) {
            new Tank(20 + i * 80, (i + 1) * 100, Utils.getRandomDirection(), Group.VILLAIN);
        }
        return initTankCount;
    }

    private void createWall() {
        for (int i = 0; i < TankFrame.GAME_HEIGHT / Wall.HEIGHT - 1; i++) {
            new Wall(600, 400 + i * Wall.HEIGHT);
            new Wall(300 + i * Wall.WIDTH, 200);
        }
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        System.out.println(numberOfEnemies);
        if (numberOfEnemies == 0) { // youWin
            BufferedImage youWin = ResourceMgr.gameOver;
            g.drawImage(ResourceMgr.youWin, TankFrame.GAME_WIDTH / 2 - youWin.getWidth() / 2, TankFrame.GAME_HEIGHT / 2 - youWin.getHeight() / 2, null);
            return;
        }
        if (!myTank.isLive()) { // GameOver
            BufferedImage gameOver = ResourceMgr.gameOver;
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

    public static void setNumberOfEnemies() {
        numberOfEnemies--;
    }

}
