package com.andy.tank;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author andy-liu
 * @date 2020/4/30 - 10:56 PM
 */
public class ResourceMgr {

    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage wall;
    public static BufferedImage[] explosion = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = Utils.rotateImage(goodTankU, -90);
            goodTankR = Utils.rotateImage(goodTankU, 90);
            goodTankD = Utils.rotateImage(goodTankU, 180);
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = Utils.rotateImage(badTankU, -90);
            badTankR = Utils.rotateImage(badTankU, 90);
            badTankD = Utils.rotateImage(badTankU, 180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletL = Utils.rotateImage(bulletU, -90);
            bulletR = Utils.rotateImage(bulletU, 90);
            bulletD = Utils.rotateImage(bulletU, 180);

            wall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/square0.jpg"));

            for (int i=0; i<16; i++){
                explosion[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
