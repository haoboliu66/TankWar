package com.andy.tank;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;


public class Bullet extends GameObject{

    private static final int SPEED = 10;
    private Direction dir;
    private boolean isLive = true;
    private Group group = Group.VILLAIN;

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet() {

    }

    public Bullet(int x, int y, Direction dir) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, Direction dir, Group group) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if(!isLive){
            /** remove bullet when it is out of range */
            System.out.println("remove!!!");
            GameModel.getInstance().remove(this);
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

    public void die(){
        isLive = false;
    }

    public boolean isLive() {
        return isLive;
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
