package com.andy.tank;

import utils.Utils;

import java.awt.*;

public class Tank extends GameObject {

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private Direction dir = Direction.DOWN;
    private final int SPEED = 5;
    private boolean moving = true;
    private boolean isLive = true;
    private Group group = Group.VILLAIN;
    private int oldX;
    private int oldY;

    private int life;
    private final int DEFAULT_LIFE = 24;
    private final int DEFUALT_LIFE_HERO = 40;


    public Tank() {
    }

    public Tank(int x, int y, Direction dir) {
        super(x, y);
        this.dir = dir;
    }

    public Tank(int x, int y, Direction dir, Group group) {
        super(x, y);
        this.dir = dir;
        this.group = group;
        this.life = DEFAULT_LIFE;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if (group == Group.HERO) {
            moving = false;
//            this.life = DEFUALT_LIFE_HERO;
        }
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!isLive) {
            GameModel.getInstance().remove(this);
            return;
        }
        drawImage(g);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, life, 5);
        g.setColor(c);

        move();
    }

    /**
     * draw tank image
     */
    private void drawImage(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.HERO ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.HERO ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.HERO ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.HERO ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
    }

    public void move() {
        if (!moving) return;
        // keep old coordinates for conflict resolution
        oldX = x;
        oldY = y;

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

        /** only VILLAIN group random fires  */
        if (this.group == Group.VILLAIN && Utils.getRandom() > 95) {
            fire();
        }
        /** VILLAIN group change directions */
        if (this.group == Group.VILLAIN && Utils.getRandom() > 90) {
            changeDiretion();
        }

        /** check boundaries */
        boundaryCheck();

        /** update x,y */
        rect.x = this.x;
        rect.y = this.y;
    }


    private void boundaryCheck() {
        if (x < 0) {
            x = 0;
        }
        if (this.y < 30) {
            y = 30;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    /**
     * change random direction
     */
    private void changeDiretion() {
        if (this.group != Group.HERO) {
            this.dir = Utils.getRandomDirection();
        }
    }

    /**
     * resolve conflict
     */
    public void resolveConflict() {
        this.x = this.oldX;
        this.y = this.oldY;
    }

    public void fire() {
        int bX = this.x;
        int bY = this.y;
        switch (dir) {
            /* different dir, different bullet positions */
            case LEFT:
                bX = this.x - Bullet.WIDTH / 2;
                bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case UP:
                bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = this.y - Bullet.HEIGHT / 2;
                break;
            case RIGHT:
                bX = this.x + Tank.WIDTH - Bullet.WIDTH / 2;
                bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case DOWN:
                bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = this.y + Tank.HEIGHT - Bullet.HEIGHT / 2;
                break;
        }
        /** add bullet by group */
        new Bullet(bX, bY, this.dir, this.getGroup());
    }

    public void die() {
        /*  tank dies and explodes  */
        Explosion explosion = new Explosion(this.x, this.y);
        this.setLive(false);
        if (this.getGroup() == Group.VILLAIN) {
            GameModel.setNumberOfEnemies();
        }
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}
