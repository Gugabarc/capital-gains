package com.mycompany.capitalgains.model;

public class WeightedAveragePrice {

    private double value;

    public WeightedAveragePrice() {
        value = 0;
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        return "WeightedAveragePrice[" + value + "]";
    }

    public void calculate(int currentStockQuantity, Operation operation) {
        value = ((currentStockQuantity * value) + (operation.getQuantity() * operation.getUnitCost())) /
                (currentStockQuantity + operation.getQuantity());
    }
}
