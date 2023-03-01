package com.mycompany.capitalgains.model;

public class Operation {

    private String operation;
    private double unitCost;
    private int quantity;

    private Operation(String operation, double unitCost, int quantity) {
        this.operation = operation;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public static Operation buyOperationWith(double unitCost, int quantity){
        return new Operation("buy", unitCost, quantity);
    }

    public static Operation sellOperationWith(double unitCost, int quantity){
        return new Operation("sell", unitCost, quantity);
    }

    public OperationType getOperation() {
        return OperationType.fromString(operation);
    }

    public double getUnitCost() {
        return unitCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public double totalCost() {
        return unitCost * quantity;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operation='" + operation + '\'' +
                ", unitCost=" + unitCost +
                ", quantity=" + quantity +
                '}';
    }
}
