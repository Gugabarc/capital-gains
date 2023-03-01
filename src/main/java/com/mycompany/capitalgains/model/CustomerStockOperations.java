package com.mycompany.capitalgains.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerStockOperations {

    private static final Logger log = LoggerFactory.getLogger(CustomerStockOperations.class);

    private Operations operations;
    private WeightedAveragePrice weightedAveragePrice;
    private AccumulatedLoss accumulatedLoss;
    private int stockQuantity;
    private final Taxes taxes;

    private CustomerStockOperations(Operations operations) {
        this.taxes = new Taxes();
        this.operations = operations;
        this.weightedAveragePrice = new WeightedAveragePrice();
        this.accumulatedLoss = new AccumulatedLoss();
        this.stockQuantity = 0;
    }

    public static CustomerStockOperations of(Operations operations) {
        return new CustomerStockOperations(operations);
    }

    public Taxes calculateTaxes(){
        for (Operation operation : operations.list()) {
            processBuyOperation(taxes, operation);
            processSell(taxes, operation);
            log.debug("Processed {}", this);
        }
        return taxes;
    }

    private void processBuyOperation(Taxes taxes, Operation operation) {
        if(operation.getOperation() != OperationType.BUY) {
            return;
        }
        weightedAveragePrice.calculate(stockQuantity, operation);
        stockQuantity += operation.getQuantity();
        taxes.add(new Tax(0));
    }

    private void processSell(Taxes taxes, Operation operation) {
        if(operation.getOperation() != OperationType.SELL){
            return;
        }
        validateQuantity(operation);
        updateQuantity(operation);
        var operationBalance = calculateOperationBalance(operation);
        if (operationBalance > 0) {
            processSellWithProfit(taxes, operation, operationBalance);
        } else {
            processSellWithLoss(taxes, operationBalance);
        }
    }

    private void processSellWithLoss(Taxes taxes, double operationBalance) {
        accumulatedLoss.add(Math.abs(operationBalance));
        taxes.add(new Tax(0));
    }

    private void processSellWithProfit(Taxes taxes, Operation operation, double operationBalance) {
        var profitToBeTaxed = calculateProfitToBeTaxed(operationBalance);
        accumulatedLoss.minus(operationBalance);
        if(profitToBeTaxed < 0){
            taxes.add(new Tax(0));
        } else {
            taxes.add(Tax.calculateTax(profitToBeTaxed, operation.totalCost()));
        }
    }

    private double calculateProfitToBeTaxed(double operationBalance) {
        return operationBalance - accumulatedLoss.value();
    }

    private double calculateOperationBalance(Operation operation) {
        return (operation.getUnitCost() - weightedAveragePrice.value()) * operation.getQuantity();
    }

    private void updateQuantity(Operation operation) {
        stockQuantity = stockQuantity - operation.getQuantity();
    }

    private void validateQuantity(Operation operation) {
        if(operation.getQuantity() > stockQuantity){
            throw new IllegalArgumentException("Stock quantity can't be negative");
        }
    }

    public Taxes taxes() {
        return taxes;
    }

    @Override
    public String toString() {
        return "CustomerStockOperations{" +
                "operations=" + operations +
                ", weightedAveragePrice=" + weightedAveragePrice +
                ", accumulatedLoss=" + accumulatedLoss +
                ", stockQuantity=" + stockQuantity +
                ", taxes=" + taxes +
                '}';
    }
}
