package com.mycompany.capitalgains;

import com.mycompany.capitalgains.infra.BufferedReaderInputReader;
import com.mycompany.capitalgains.infra.GsonJsonConverter;

public class CapitalGainsApplication {

    public static void main(String[] args) {
        CapitalGainsInput input = new CapitalGainsInput(new GsonJsonConverter(), new BufferedReaderInputReader());
        input.execute();
    }
}