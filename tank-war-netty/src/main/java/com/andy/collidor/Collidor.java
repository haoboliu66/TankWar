package com.andy.collidor;

import com.andy.tank.components.GameObject;

/**
 * @author andy-liu
 * @date 2020/5/16 - 9:51 AM
 */
public interface Collidor {

    boolean collideWith(GameObject o1, GameObject o2);

}
