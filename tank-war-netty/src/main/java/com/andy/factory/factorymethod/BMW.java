package com.andy.factory.factorymethod;

/**
 * @author andy-liu
 * @date 2020/5/26 - 8:48 PM
 */
public class BMW extends Car{

    public BMW() {
        System.out.println("BMW");
    }

    public BMW(double price, double weight) {
        super(price, weight);
    }

}
