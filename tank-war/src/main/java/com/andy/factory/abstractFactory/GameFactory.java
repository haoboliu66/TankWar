package com.andy.factory.abstractFactory;

import com.andy.tank.components.Direction;
import com.andy.tank.components.Group;
import com.andy.tank.TankFrame;

/**
 * @author andy-liu
 * @date 2020/5/5 - 12:32 PM
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Direction dir, TankFrame tf, Group group);

    public abstract BaseBullet createBullet(int x, int y, Direction dir, TankFrame tf, Group group);

    public abstract BaseExplosion createExplosion(int x, int y, TankFrame tf);


}
