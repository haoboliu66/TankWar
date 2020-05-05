package com.andy.tank.abstractFactory;

import com.andy.tank.Tank;

import java.awt.*;

/**
 * @author andy-liu
 * @date 2020/5/5 - 12:33 PM
 */
public abstract class BaseBullet{

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank t);

}
