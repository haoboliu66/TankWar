package com.andy.decorator;

import com.andy.tank.GameModel;
import com.andy.tank.components.Tank;

import java.awt.*;

/**
 * @author andy-liu
 * @date 2020/5/30 - 12:00 AM
 */
public class TankDecorator extends GameObjectDecorator {

    private Tank tank;

    public TankDecorator(Tank tank) {
        super(tank);
        this.tank = tank;
//        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, tank.getY(), 25, 5);
        g.setColor(c);
    }

    public void checkDecorator() {
        if (!this.tank.isLive()) {
            GameModel.getInstance().remove(this);
        }
    }
}
