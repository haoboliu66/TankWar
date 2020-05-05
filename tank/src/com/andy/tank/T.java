package com.andy.tank;



public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt(PropertyMgr.getInstance().getProperty("initTankCount"));

        //initialize enemies tank
        for (int i=0;i<initTankCount;i++){
            tf.enemies.add(new Tank(20 + i*35,(i + 1) * 30,Utils.getRandomDirection(), tf, Group.VILLAIN));
        }

        while(true){
            Thread.sleep(50);
            tf.repaint(); //invoke paint()
        }


    }
}
