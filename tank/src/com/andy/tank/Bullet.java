package com.andy.tank;

import java.awt.*;


public class Bullet {

    private static final int SPEED = 10;
    private Direction dir;
    private int x,y;
    private boolean isLive = true;
    private Group group = Group.VILLAIN;
    Rectangle rect = new Rectangle();

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    TankFrame tf;

    public Bullet() {
    }

    public Bullet(int x, int y, Direction dir, TankFrame tf) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public Bullet(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if(!isLive){
            /** remove bullet when it is out of range */
            tf.bullets.remove(this);
        }
        drawImage(g);
        move();
    }

    /** draw bullet image */
    private void drawImage(Graphics g){
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
    }

    /** bullet move  */
    private void move(){
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if(x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT){
            isLive = false;
        }
        /** update x,y */
        rect.x = this.x;
        rect.y = this.y;
    }

    /** collision test  */
    public void collideWith(Tank tank){
        if(this.group == tank.getGroup()){
            return;
        }

        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
        }
    }



    private void die(){
        setLive(false);
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


}
