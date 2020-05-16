package com.andy.decorator;

import com.andy.tank.GameObject;

import java.awt.*;

/**
 * @author andy-liu
 * @date 2020/5/16 - 7:15 PM
 */
public class GameObjectDecorator extends GameObject{

    GameObject gameObject;

    public GameObjectDecorator(GameObject go) {
        gameObject = go;
    }


    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
    }
}
