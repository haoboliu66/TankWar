package com.andy;


import com.andy.tank.TankFrame;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tf.repaint(); //invoke paint()
        }


    }


}
