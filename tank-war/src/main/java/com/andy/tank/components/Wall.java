package com.andy.tank.components;

import com.andy.loader.ResourceMgr;
import com.andy.tank.GameModel;

import java.awt.*;

/**
 * @author andy-liu
 * @date 2020/5/16 - 1:20 PM
 */
public class Wall extends GameObject {

    public static int WIDTH = ResourceMgr.wall.getWidth();
    public static int HEIGHT = ResourceMgr.wall.getHeight();

    public Wall() {
    }

    public Wall(int x, int y){
        super(x, y);
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH + 10;
        rect.height = HEIGHT + 10;

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.wall,x,y,null);
    }


}
