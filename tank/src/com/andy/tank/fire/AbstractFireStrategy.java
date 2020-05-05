package com.andy.tank.fire;

import com.andy.tank.Bullet;
import com.andy.tank.Direction;
import com.andy.tank.Tank;

/**
 * @author andy-liu
 * @date 2020/5/5 - 9:07 AM
 */
public abstract class AbstractFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {

    }

    @Override
    public void fire(Tank t, String fs) {
        int bX = t.getX();
        int bY = t.getY();
        if (fs.equals("DEFAULT")){
            switch (t.getDir()){
                /* different dir, different bullet positions */
                case LEFT:
                    bX = t.getX() - Bullet.WIDTH / 2;
                    bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                    break;
                case UP:
                    bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                    bY = t.getY() - Bullet.HEIGHT / 2;
                    break;
                case RIGHT:
                    bX = t.getX() + Tank.WIDTH - Bullet.WIDTH / 2;
                    bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                    break;
                case DOWN:
                    bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                    bY = t.getY() + Tank.HEIGHT - Bullet.HEIGHT / 2;
                    break;
            }
            /** add bullet by group  */
//            new Bullet(bX, bY, t.getDir(), t.getTf(), t.getGroup());

        }else if(fs.equals("FOUR_DIRECTION")){
            System.out.println("four dirsss");
            Direction[] dirs = Direction.values();
            for(Direction dir: dirs) {
//                new Bullet(bX + Tank.WIDTH / 2 - Bullet.WIDTH / 2, bY + Tank.HEIGHT / 2 -Bullet.HEIGHT / 2, dir, t.getTf(), t.getGroup());

            }

        }else if(fs.equals("MISSLE")){

        }


    }


}
