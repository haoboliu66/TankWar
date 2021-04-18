package com.andy.tank.fire;

import com.andy.tank.components.Tank;

/**
 * @author andy-liu
 * @date 2020/5/5 - 8:51 AM
 */
public class FireStrategySelector extends AbstractFireStrategy{

    @Override
    public void fire(Tank t) {
        this.fire(t,"DEFAULT");
    }

    @Override
    public void fire(Tank t, String fs) {
        super.fire(t, fs);
    }
}
