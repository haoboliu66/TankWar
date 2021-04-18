package com.andy.tank.components;

import com.andy.loader.ResourceMgr;
import com.andy.tank.GameModel;
import com.andy.tank.TankFrame;

import java.awt.*;


public class Bullet extends GameObject {

    private static final int SPEED = 10;
    private Direction dir;
    private boolean isLive = true;
    private Group group = Group.VILLAIN;

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int power;
    private final int DEFAULT_POWER = 8;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Bullet() {

    }

    public Bullet(int x, int y, Direction dir) {
        super(x, y);
        this.dir = dir;
    }

    public Bullet(int x, int y, Direction dir, Group group) {
        super(x, y);
        this.dir = dir;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        this.power = DEFAULT_POWER;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!isLive) {
            /** remove bullet when it is out of range */
            GameModel.getInstance().remove(this);
        }
        drawImage(g);
        move();
    }

    /**
     * draw bullet image
     */
    private void drawImage(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
    }

    /**
     * bullet move
     */
    private void move() {
        switch (dir) {
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

        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
            isLive = false;
        }
        /** update x,y */
        rect.x = this.x;
        rect.y = this.y;
    }

    public void die() {
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
