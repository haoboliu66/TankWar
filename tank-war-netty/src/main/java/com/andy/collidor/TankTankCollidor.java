package com.andy.collidor;

import com.andy.tank.components.GameObject;
import com.andy.tank.components.Tank;

/**
 * @author andy-liu
 * @date 2020/5/16 - 9:52 AM
 */
public class TankTankCollidor implements Collidor {


    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {

        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.resolveConflict();
                t2.resolveConflict();
            }

        }
        return false;
    }
}
