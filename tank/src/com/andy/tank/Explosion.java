package com.andy.tank;

import java.awt.*;

/**
 * @author andy-liu
 * @date 2020/5/1 - 1:22 PM
 */
public class Explosion {

    private int x, y;
    public static int WIDTH = ResourceMgr.explosion[0].getWidth();
    public static int HEIGHT = ResourceMgr.explosion[0].getHeight();
    GameModel gm = null;

    private int step = 0;

    public Explosion() {
    }

//    public Explosion(int x, int y){
//        this.x = x;
//        this.y = y;
//    }

    public Explosion(int x, int y, GameModel gm) {
        System.out.println("in expo, gm ==" + gm);
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explosion[step++],x,y,null);
        if(step >= ResourceMgr.explosion.length){
            gm.explosions.remove(this);
        }
    }




}
