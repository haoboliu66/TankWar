package com.andy.factory.factorymethod;

import java.lang.reflect.InvocationTargetException;

/**
 * @author andy-liu
 * @date 2020/5/26 - 8:48 PM
 */
public class CarFactory {

    public static Car createCar(Class c){
        Car car = null;

        try {
           car = (Car) Class.forName(c.getName()).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return car;
    }

    public static void main(String[] args) {

        Car c = CarFactory.createCar(Audi.class);
        System.out.println(c);


    }
}
