package com.mycompany.capitalgains.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Taxes {

    private final List<Tax> taxes;

    public Taxes() {
        this.taxes = new ArrayList<>();
    }

    public void add(Tax tax){
        taxes.add(tax);
    }

    public List<Tax> taxes() {
        return Collections.unmodifiableList(taxes);
    }

    @Override
    public String toString() {
        return "Taxes[" + taxes + "]";
    }
}
