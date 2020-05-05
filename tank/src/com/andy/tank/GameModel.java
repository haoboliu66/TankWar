package com.andy.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andy-liu
 * @date 2020/5/5 - 5:17 PM
 */
public class GameModel {

    private static final GameModel gm = new GameModel();

    Tank myTank = new Tank(200,500,Direction.DOWN,this, Group.HERO);

    java.util.List<Bullet> bullets = new ArrayList<>(); // list for bullets
    java.util.List<Tank> enemies = new ArrayList<>(); // list for other tanks
    List<Explosion> explosions = new ArrayList<>(); // list for explosions

    public GameModel() {
        System.out.println("gggg");
        int initTankCount = Integer.parseInt(PropertyMgr.getInstance().getProperty("initTankCount"));
        //initialize enemies tank
        for(int i=0;i<initTankCount; i++){
            enemies.add(new Tank(20 + i*35,(i + 1) * 30,Utils.getRandomDirection(), this, Group.VILLAIN));
        }
    }


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

    public Tank getMyTank() {
        return myTank;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Explosion> getExplosions() {
        return explosions;
    }

    public static GameModel getInstance(){
        return gm;
    }






}
