package com.revature.Server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PrintOutServlet extends HttpServlet {
//    private FileRepository fileRepository;
    private List<String> stockLine;
    private InputStream file;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.fileRepository = new FileRepository("result.csv");
        this.stockLine = new ArrayList<>();
        this.file = getClass().getClassLoader().getResourceAsStream("result.csv");
        Scanner scanner = new Scanner(this.file);
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            this.stockLine.add(scanner.next());
        }
        for (String stock : this.stockLine) {
            resp.getWriter().println(stock);
        }
    }
}
