package com.andy.tank.abstractFactory;

import com.andy.tank.*;

/**
 * @author andy-liu
 * @date 2020/5/5 - 12:34 PM
 */
public class DefaultFactory extends GameFactory{

    private static final DefaultFactory df = new DefaultFactory();

    private DefaultFactory() {
    }

    public static DefaultFactory getInstance(){
        return df;
    }

    @Override
    public BaseTank createTank(int x, int y, Direction dir, TankFrame tf, Group group) {
        return new Tank(x, y, dir, tf, group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction dir, TankFrame tf, Group group) {
        return new Bullet(x, y, dir, tf, group);
    }

    @Override
    public BaseExplosion createExplosion(int x, int y, TankFrame tf) {
        return new Explosion(x, y, tf);
    }
}
