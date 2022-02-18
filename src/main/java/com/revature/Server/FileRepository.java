package com.revature.Server;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRepository {
    private List<String> stockLine;
    private InputStream file;

    public FileRepository() {
    }

//    public FileRepository(String filename) {
//        this.stockLine = new ArrayList<>();
//        this.file = getClass().getClassLoader().getResourceAsStream(filename);
//        load();
//    }

    private void load(String filename) {
        this.stockLine = new ArrayList<>();
        this.file = getClass().getClassLoader().getResourceAsStream(filename);
        Scanner scanner = new Scanner(this.file);
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            this.stockLine.add(scanner.next());
        }
    }

    public List<String> getStockLine() {
        return stockLine;
    }

}
