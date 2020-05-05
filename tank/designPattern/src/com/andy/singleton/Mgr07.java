package com.andy.singleton;

/**
 * 静态内部类
 * JVM保证单例
 * 加载外部类时不会加载内部类, 可以实现lazy loading
 * 比Mgr01稍完美
 */
public class Mgr07 {

    private Mgr07(){

    }
    //静态内部类不会随着外部类的加载而加载, JVM保证每个类只加载一次
    private static class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance(){
        return Mgr07Holder.INSTANCE;
    }

}
