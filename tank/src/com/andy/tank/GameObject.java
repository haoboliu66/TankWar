package com.andy.tank;

import java.awt.*;

/**
 * @author andy-liu
 * @date 2020/5/5 - 6:50 PM
 */
public abstract class GameObject {

    protected int x;
    protected int y;
    protected Rectangle rect;

    public GameObject() {
        rect = new Rectangle();
    }

    public abstract void paint(Graphics g);

//    public abstract boolean collideWith(GameObject o);


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Rectangle getRect() {
        return rect;
    }

}
