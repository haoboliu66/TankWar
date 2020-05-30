package com.andy.collidor;

import com.andy.tank.GameObject;
import com.andy.tank.Tank;
import com.andy.tank.Wall;

/**
 * @author andy-liu
 * @date 2020/5/16 - 2:25 PM
 */
public class TankWallCollidor implements Collidor{

    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank tank = (Tank)o1;
            Wall wall = (Wall)o2;
            if(tank.getRect().intersects(wall.getRect())){
                tank.resolveConflict();
            }
        }
        if(o1 instanceof Wall && o2 instanceof Tank){
            collideWith(o2,o1);
        }
        return false;
    }

}
