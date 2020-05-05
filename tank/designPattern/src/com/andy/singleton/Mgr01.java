package com.andy.singleton;

/**
 * 饿汉式 eager loading
 * 类加载到内存后, 就实例化一个单利, JVM保证线程安全
 * 简单实用: 推荐
 * 唯一缺点: 不管用到与否, 类加载时就完成实例化
 */
public class Mgr01 {

    //final必须初始化或在静态代码块中初始化
    private static final Mgr01 INSTANCE = new Mgr01();

    //private constructor
    private Mgr01(){

    }

    public static Mgr01 getInstance(){
        return INSTANCE;
    }


}
