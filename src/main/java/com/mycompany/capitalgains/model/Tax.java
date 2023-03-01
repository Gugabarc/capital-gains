package com.mycompany.capitalgains.model;

public class Tax {
    private double value;

    public Tax(double value) {
        this.value = value;
    }

    public static Tax calculateTax(double profitToTax, double totalCost) {
        if(totalCost <= 20000){
            return new Tax(0);
        }
        return new Tax(profitToTax * 0.2);
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        return "Tax[" + value + "]";
    }
}
