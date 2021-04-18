package com.andy.collidor;

import com.andy.tank.components.Bullet;
import com.andy.tank.GameModel;
import com.andy.tank.components.GameObject;
import com.andy.tank.components.Tank;

/**
 * @author andy-liu
 * @date 2020/5/16 - 9:52 AM
 */
public class TankBulletCollidor implements Collidor {

    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        Tank tank = null;
        Bullet bullet = null;
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            tank = (Tank) o1;
            bullet = (Bullet) o2;
        }
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            collideWith(o2, o1);
        }
        if (bullet != null && tank != null) {
            if (bullet.getGroup() == tank.getGroup()) {
                return false;
            }
            // Setting: Hero never dies, to be discarded
//            if (tank.getGroup() == Group.HERO) {
//                return false;
//            }

            if (bullet.getRect().intersects(tank.getRect())) {
                if (tank.getLife() - bullet.getPower() > 0) {
                    tank.setLife(tank.getLife() - bullet.getPower());
                } else {
                    tank.die();
                    GameModel.setNumberOfEnemies(tank);
                }
                bullet.die();
                return true;
            }
        }
        return false;
    }

}
