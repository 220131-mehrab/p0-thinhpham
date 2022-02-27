package com.revature.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class InputStockService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String HTMLForm = "<Html>\n" +
                "<Head>\n" +
                "    <Title>Stock History" +
                "</Title>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<h1>Welcome to Stock History App</h1>\n" +
                "<p>This app will allow to see the price of a stock</p>\n" +
                "<p>Input the stock ticker. Ex: IBM, AAPL, or VOO</p>\n" +
                "<p>Date for as yyyy-mm-dd</p>\n" +
                "<form action='/receive' method='get'>\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"ticker\">Stock Ticker: </label>\n" +
                "        <input type=\"text\" name=\"ticker\" id=\"ticker\" required>\n" +
                "    </div>\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"startDate\">Start Date: </label>\n" +
                "        <input type=\"text\" name=\"startDate\" id=\"startDate\" required>\n" +
                "    </div>\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"endDate\">End Date: </label>\n" +
                "        <input type=\"text\" name=\"endDate\" id=\"endDate\" required>\n" +
                "    </div" +
                ">\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"interval\">Interval (You can type: yearly, monthly, or daily): </label>\n" +
                "        <input type=\"text\" name=\"interval\" id=\"interval\" required>\n" +
                "    </div>\n" +
                "    <input type=\"submit\" value=\"Submit\"/>\n" +
                "</form>\n" +
                "</Body>\n" +
                "</Html>";
        resp.getWriter().println(HTMLForm);

    }


}
