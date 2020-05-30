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
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        rect = new Rectangle();
    }

    public abstract void paint(Graphics g);


    public Rectangle getRect() {
        return rect;
    }

}
