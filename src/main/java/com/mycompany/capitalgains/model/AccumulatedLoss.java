package com.mycompany.capitalgains.model;

public class AccumulatedLoss {

    private double value;

    public AccumulatedLoss() {
        value = 0;
    }

    public void minus(double v){
        value = value - v;
        if(value < 0){
            value = 0;
        }
    }

    public void add(double v){
        value = value + v;
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        return "AccumulatedLoss[" + value + "]";
    }
}
