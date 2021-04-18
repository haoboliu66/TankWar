package com.andy.factory.factorymethod;

/**
 * @author andy-liu
 * @date 2020/5/26 - 8:48 PM
 */
public class Benz extends Car{

    public Benz() {
        System.out.println("Benz");
    }

    public Benz(double price, double weight) {
        super(price, weight);
    }

}
