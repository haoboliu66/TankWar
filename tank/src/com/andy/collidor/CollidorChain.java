package com.andy.collidor;

import com.andy.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author andy-liu
 * @date 2020/5/16 - 10:09 AM
 */
public class CollidorChain implements Collidor{

    private List<Collidor> collidors;

    public CollidorChain() {
        collidors = new LinkedList<>();
        collidors.add(new TankBulletCollidor());
        collidors.add(new TankTankCollidor());
        collidors.add(new TankWallCollidor());
    }


    public boolean collideWith(GameObject o1, GameObject o2){
        for(Collidor c: collidors){
            if(c.collideWith(o1,o2)){
                return true;
            }
        }
        return false;
    }

}
