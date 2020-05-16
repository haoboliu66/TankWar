package com.andy.collidor;

import com.andy.tank.GameObject;
import com.andy.tank.PropertyMgr;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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

        String colliors = PropertyMgr.getInstance().getProperty("colliors");
        String[] names = colliors.split(",");

        try {
            for(String className: names){
                collidors.add((Collidor) Class.forName(className).getDeclaredConstructor().newInstance());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public boolean collideWith(GameObject o1, GameObject o2){
        for(Collidor c: collidors){
            if(c.collideWith(o1,o2)){
                break;
            }
        }
        return false;
    }

}
