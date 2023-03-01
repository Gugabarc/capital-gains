package com.mycompany.capitalgains;

import com.mycompany.capitalgains.infra.TaxResponse;
import com.mycompany.capitalgains.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CapitalGainsInput {

    private final JsonConverter jsonConverter;
    private final InputReader inputReader;

    public CapitalGainsInput(JsonConverter jsonConverter, InputReader inputReader) {
        this.jsonConverter = jsonConverter;
        this.inputReader = inputReader;
    }

    public void execute() {
        String line;
        while ((line = inputReader.readLine()) != null && !line.isEmpty()) {
            Operations operations = new Operations();
            CustomerStockOperations customer = CustomerStockOperations.of(operations);
            Operation[] inputOperations = jsonConverter.fromJson(line, Operation[].class);
            List.of(inputOperations).stream()
                    .forEach(operation -> operations.add(operation));
            customer.calculateTaxes();
            Taxes taxes = customer.taxes();

            List<TaxResponse> response = taxes.taxes()
                    .stream()
                    .map(t -> new TaxResponse(t.value()))
                    .collect(Collectors.toList());
            System.out.println(jsonConverter.toJson(response));
        }



    }
}