package com.revature.Servlet;

import com.revature.Server.DatabaseController;
import com.revature.domain.MyStock;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class PrintOutServlet extends HttpServlet {

    /**
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
        String HTMLTitle = "<h3" +
                ">Symbol, Date, Open Price, High Price, Low Price, Closed Price</h3>";
        if (type != null) {
            resp.getWriter().println(HTMLTitle);
            List<MyStock> stockList = new DatabaseController().PrintOutBySort(type);
            stockList.forEach(myStock -> {
                String HTMLContent = myStock.getName() + "\t " + myStock.getDate() + "\t, "
                        + myStock.getOpen() + "\t, " + myStock.getHigh() + "\t, " + myStock.getLow()
                        + "\t, " + myStock.getClose() + ".";
                String convertContent = "<p>" + HTMLContent + "</p>" + "\n";
                try {
                    resp.getWriter().println(convertContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (startDay != null && endDay != null) {
            resp.getWriter().println(HTMLTitle);
            List<MyStock> stockList = new DatabaseController().PrintOutByTime(startDay, endDay);
            stockList.forEach(myStock -> {
                String HTMLContent = myStock.getName() + "\t " + myStock.getDate() + "\t, "
                        + myStock.getOpen() + "\t, " + myStock.getHigh() + "\t, " + myStock.getLow()
                        + "\t, " + myStock.getClose() + ".";
                String convertContent = "<p>" + HTMLContent + "</p>" + "\n";
                try {
                    resp.getWriter().println(convertContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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

        //language=HTML
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
