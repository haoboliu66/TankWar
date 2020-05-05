package com.andy.singleton;

/**
 * 枚举类, 可以防止反序列化(枚举类没有构造方法)
 */
public enum Mgr08 {

    INSTANCE;

    public static void main(String[] args) {
        for(int i = 0; i<100; i++){
            new Thread(()->{
                System.out.println(Mgr08.INSTANCE.hashCode());
            }).start();
        }
    }

}
