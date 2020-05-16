package com.andy.tank;

import com.andy.collidor.CollidorChain;
import utils.Utils;

import java.awt.*;
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

    private static GameModel GM_INSTANCE  = new GameModel();

    static {
        GM_INSTANCE.init();
    }

    Tank myTank;
    private List<GameObject> objects = new ArrayList<>();
    CollidorChain chain = new CollidorChain();

    private GameModel() {
    }

    public void init(){
        myTank = new Tank(100,600,Direction.DOWN, Group.HERO);
        initializeTanks();
        createWall();
    }


    private void initializeTanks(){
        int initTankCount = Integer.parseInt(PropertyMgr.getInstance().getProperty("initTankCount"));
        //initialize enemies tank
        for(int i=0;i<initTankCount; i++){
            new Tank(20 + i*80,(i + 1) * 100, Utils.getRandomDirection(), Group.VILLAIN);
        }
    }


    private void createWall(){
        for(int i=0; i < TankFrame.GAME_HEIGHT / Wall.HEIGHT - 1; i++){
            new Wall(600,400 + i * Wall.HEIGHT);
            new Wall(300 + i * Wall.WIDTH , 200);
        }
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }


    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(c);

        /** paint objects  */
        for(int i=0; i<objects.size(); i++){
            objects.get(i).paint(g);
        }

        /** collision test   */
        for(int i=0; i<objects.size(); i++){
            for(int j=i+1;j<objects.size();j++){
               chain.collideWith(objects.get(i),objects.get(j));
            }
        }


//        for(Iterator<Bullet> it = bullets.iterator(); it.hasNext()){
//            Bullet b = it.next();
//            if(!b.isLive()) it.remove();
//        }
    }

    public Tank getMyTank() {
        return myTank;
    }

    public static GameModel getInstance(){
        return GM_INSTANCE;
    }



}
