package com.andy.tank.fire;

import com.andy.tank.components.Tank;

/**
 * @author andy-liu
 * @date 2020/5/5 - 12:26 AM
 */
public interface FireStrategy {

    void fire(Tank t);
    void fire(Tank t, String fs);

}
