package com.andy.tank;

import com.andy.collidor.CollidorChain;

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

    private static final GameModel gm = new GameModel();

    Tank myTank = new Tank(100,600,Direction.DOWN, this,Group.HERO);

    private List<GameObject> objects = new ArrayList<>();

    CollidorChain chain = new CollidorChain();

    public GameModel() {
        int initTankCount = Integer.parseInt(PropertyMgr.getInstance().getProperty("initTankCount"));
        //initialize enemies tank
        for(int i=0;i<initTankCount; i++){
            objects.add(new Tank(20 + i*80,(i + 1) * 100,Utils.getRandomDirection(),this, Group.VILLAIN));
        }

        for(int i=0; i < TankFrame.GAME_HEIGHT / Wall.HEIGHT; i++){
            objects.add(new Wall(600,400 + i * Wall.HEIGHT));
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
//        g.drawString("num of bullets: " + bullets.size(),10,60);
//        g.drawString("num of enemies: " + enemies.size(),10,80);
//        g.drawString("num of explosions: " + explosions.size(),10,100);
        g.setColor(c);

        myTank.paint(g);

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
        return gm;
    }






}
