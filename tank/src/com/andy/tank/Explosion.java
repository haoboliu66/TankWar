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
    TankFrame tf = null;

    private int step = 0;

    public Explosion() {
    }

    public Explosion(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explosion[step++],x,y,null);
        if(step >= ResourceMgr.explosion.length){
            tf.explosions.remove(this);
        }
    }




}
