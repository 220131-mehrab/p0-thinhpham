package com.revature.Server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class testClassSerlet extends HttpServlet {
    private FileRepository fileRepository = new FileRepository("result.csv");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (String stock : fileRepository.getStockLine()) {
            resp.getWriter().println(stock);
        }
    }
}
