package com.mycompany.capitalgains.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    private final List<Operation> list;

    public Operations() {
        this.list = new ArrayList<>();
    }

    public void add(Operation operation){
        list.add(operation);
    }

    public List<Operation> list() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return "Operations[" + list + "]";
    }
}
