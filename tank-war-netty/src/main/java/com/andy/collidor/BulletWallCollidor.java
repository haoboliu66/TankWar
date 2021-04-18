package com.andy.collidor;

import com.andy.tank.components.Bullet;
import com.andy.tank.components.GameObject;
import com.andy.tank.components.Wall;


/**
 * @author andy-liu
 * @date 2020/5/16 - 3:17 PM
 */
public class BulletWallCollidor implements Collidor{

    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall)o2;
            if(bullet.getRect().intersects(wall.getRect())){
                bullet.die();
            }
        }
        if(o1 instanceof Wall && o2 instanceof Bullet){
            collideWith(o2,o1);
        }

        return false;
    }
}
