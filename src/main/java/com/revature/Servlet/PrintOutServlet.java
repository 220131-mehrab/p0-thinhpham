package com.revature.Servlet;

import com.revature.Server.DatabaseController;
import com.revature.domain.MyStock;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


/**
 * This servlet is to print out sorted records and records in a peroid of time
 */
public class PrintOutServlet extends HttpServlet {

    /**
     * This method will print out HTML of sorted records and records in a peroid of time
     * It also ask users to input the period of time
     * It also provide a button to return to the homepage
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("nameTime");
        String startDay = req.getParameter("startDate");
        String endDay = req.getParameter("endDate");
        //language=HTML
        String head = "<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "  text-align: center;\n" +
                "}\n" +
                "</style>\n" +
                "<table style=\"width:60%\">\n" +
                "    <tr>\n" +
                "        <th>No</th>\n" +
                "        <th>Symbol" +
                "</th>\n" +
                "        <th>Date</th>\n" +
                "        <th>Open Price</th>\n" +
                "        <th>High Price</th>\n" +
                "        <th>Low Price</th>\n" +
                "        <th>Close Price</th>\n" +
                "    " +
                "</tr>\n";
        String body = "";
        String tail = "</table>";
        int i = 1;

        if (type != null) {
            List<MyStock> stockList = new DatabaseController().PrintOutBySort(type);
            for (MyStock stock : stockList) {
                String content = "<tr><td>" + i + "</td><td>" + stock.getName() + "</td><td>" + stock.getDate() + "</td><td>" +
                        stock.getOpen() + "</td><td>" + stock.getHigh() + "</td><td>" + stock.getLow() + "</td><td>" +
                        stock.getClose() + "</td> </tr>";
                body += content;
                i++;
            }
            try {
                resp.getWriter().println(head + body + tail);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (startDay != null && endDay != null) {
            List<MyStock> stockList = new DatabaseController().PrintOutByTime(startDay, endDay);
            for (MyStock stock : stockList) {
                String content = "<tr><td>" + i + "</td><td>" + stock.getName() + "</td><td>" + stock.getDate() + "</td><td>" +
                        stock.getOpen() + "</td><td>" + stock.getHigh() + "</td><td>" + stock.getLow() + "</td><td>" +
                        stock.getClose() + "</td> </tr>";
                body += content;
                i++;
            }
            try {
                resp.getWriter().println(head + body + tail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String HTMLFindMoreStockForm = "<Html>\n" +
                "<Head>\n" +
                "    <Title>Stock History</Title>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<form action=\"/home\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Find more stock history\" name=\"homeRedirect\">\n" +
                "    <br" +
                ">\n" +
                "</form>\n" +
                "</Body>\n" +
                "</Html>\n" +
                "\n" +
                "\n";
        resp.getWriter().println(HTMLFindMoreStockForm);

        String HTMLStockSearchForm = "<Html>\n" +
                "<Head>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<p>Select how you want to print out by time</p>\n" +
                "<p>Time format is yyyy-mm-dd</p>\n" +
                "<form action='/printout' method='get'>\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"startDate\">Start Date: </label>\n" +
                "        <input type=\"text\" name=\"startDate\" id=\"startDate\" required>\n" +
                "    </div>\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"endDate\">End Date: </label>\n" +
                "        <input type=\"text\" name=\"endDate\" id=\"endDat" +
                "e\" required>\n" +
                "    </div>\n" +
                "    <input type=\"submit\" value=\"Submit\"/>\n" +
                "</form>\n" +
                "</Body>\n" +
                "</Html>";
        resp.getWriter().println(HTMLStockSearchForm);
    }
}
