package com.andy.singleton;

/**
 * 懒汉式 lazy loading
 *
 *  缺点: race condition(thread unsafe)
 *  // 加锁
 */

public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04(){

    }

    public static synchronized Mgr04 getInstance(){
        if(INSTANCE == null){
            // race condition
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(()->{
                //同一个类的不同对象, HashCode相同; 但是不同类的不同对象可能HashCode相同
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }

}
