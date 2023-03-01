package com.mycompany.capitalgains.model;

public enum OperationType {
    BUY("buy"),
    SELL("sell");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public static OperationType fromString(String value) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.value.equalsIgnoreCase(value)) {
                return operationType;
            }
        }
        throw new IllegalArgumentException("No enum constant " + OperationType.class.getCanonicalName() + "." + value);
    }
}
