package com.mycompany.capitalgains.infra;

import com.mycompany.capitalgains.model.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderInputReader implements InputReader {

    @Override
    public String readLine() {
        try {
            System.out.println("Write operations:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}