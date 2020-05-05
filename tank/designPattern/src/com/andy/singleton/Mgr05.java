package com.andy.singleton;

/**
 * 此写法不可行
 */
public class Mgr05 {

    private static Mgr05 INSTANCE;

    private Mgr05(){

    }

    public static synchronized Mgr05 getInstance(){
        if(INSTANCE == null){
            // 无法避免 race condition
            synchronized (Mgr05.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
            }
            }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(()->{
                //同一个类的不同对象, HashCode相同; 但是不同类的不同对象可能HashCode相同
                System.out.println(Mgr05.getInstance().hashCode());
            }).start();
        }
    }
}
