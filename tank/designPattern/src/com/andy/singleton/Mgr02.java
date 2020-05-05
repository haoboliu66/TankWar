package com.andy.singleton;

/**
 * Same with Mgr01
 */
public class Mgr02 {

    private static final Mgr02 INSTANCE;

    static {
        INSTANCE = new Mgr02();
    }
    //private constructor
    private Mgr02(){

    }

    public static Mgr02 getInstance(){
        return INSTANCE;
    }
}
