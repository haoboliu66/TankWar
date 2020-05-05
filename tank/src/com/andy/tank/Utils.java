package com.andy.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author andy-liu
 * @date 2020/5/1 - 11:12 AM
 */
public class Utils {

        private static Random random = new Random();

        public static Direction getRandomDirection(){
            Direction[] directions = Direction.values();
            int randomFactor = random.nextInt(4);
            return directions[randomFactor];
        }

        public static int getRandom(){
            return random.nextInt(100);
        }

        /** rotate image  */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

}
