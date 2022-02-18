package com.revature.Server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReceiveInputServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticker = req.getParameter("ticker");
        System.out.println("Ticker: " + ticker);
        String startDate = req.getParameter("startDate");
        System.out.println("Start: " + startDate);
        String endDate = req.getParameter("endDate");
        System.out.println("End: " + endDate);
        String interval = req.getParameter("interval");
        System.out.println("Interval: " + interval);
    }
}
