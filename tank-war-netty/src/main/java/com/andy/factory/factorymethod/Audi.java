package com.andy.factory.factorymethod;

/**
 * @author andy-liu
 * @date 2020/5/26 - 8:48 PM
 */
public class Audi extends Car{

    public Audi() {
        System.out.println("Audi");
    }

    public Audi(double price, double weight) {
        super(price, weight);
    }

    public void say(){
        System.out.println("hello audi");
    }

    @Override
    public String toString() {
        return "Audi{" +
                "price=" + price +
                ", weight=" + weight +
                '}';
    }
}
