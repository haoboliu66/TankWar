package com.andy.loader;

import java.io.IOException;
import java.util.Properties;

/**
 * @author andy-liu
 * @date 2020/5/1 - 8:01 PM
 */
public class PropertyMgr {

    private static Properties props = null;

    private PropertyMgr() {

    }

    static{
        try {
            props = getInstance();
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance(){
        if(props == null){
            props = new Properties();
        }
        return props;
    }



}
