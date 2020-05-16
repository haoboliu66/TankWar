package com.andy.collidor;

import com.andy.tank.Bullet;
import com.andy.tank.GameObject;
import com.andy.tank.Tank;

/**
 * @author andy-liu
 * @date 2020/5/16 - 9:52 AM
 */
public class TankBulletCollidor implements Collidor {

    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        Tank tank = null;
        Bullet bullet = null;
        if(o1 instanceof Tank && o2 instanceof Bullet){
            tank = (Tank) o1;
            bullet = (Bullet) o2;
        }
        if(o1 instanceof Bullet && o2 instanceof Tank){
            collideWith(o2,o1);
        }
        if(bullet != null && tank != null){
            bullet.collideWith(tank);
        }

        return false;
    }

}
