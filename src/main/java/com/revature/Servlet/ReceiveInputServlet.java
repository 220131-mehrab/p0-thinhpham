package com.revature.Servlet;

import com.revature.Server.DatabaseController;
import com.revature.domain.MyStock;
import com.revature.domain.StockCommands;
import com.revature.YahooApi.YahooStockAPI;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public class ReceiveInputServlet extends HttpServlet {

    private StockCommands stockCommands;
    private YahooStockAPI yahooStockAPI;

    /**
     *
     */
    public ReceiveInputServlet() {
        this.yahooStockAPI = new YahooStockAPI();
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticker = req.getParameter("ticker");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String interval = req.getParameter("interval");
        stockCommands = new StockCommands(ticker, startDate, endDate, interval);
        List<MyStock> stockList = this.yahooStockAPI.getHistory(this.stockCommands.getTicker(),
                this.stockCommands.getStartDate(), this.stockCommands.getEndDate(), this.stockCommands.getInterval());
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

        for (MyStock index : stockList) {
            String name = index.getName();
            String time = index.getDate();
            BigDecimal open = index.getOpen();
            BigDecimal high = index.getHigh();
            BigDecimal low = index.getLow();
            BigDecimal close = index.getClose();
            new DatabaseController().InsertData(name, time, open, high, low, close);
            String content = "<tr><td>" + i + "</td><td>" + name + "</td><td>" + time + "</td><td>" + open
                    + "</td><td>" + high + "</td><td>" + low + "</td><td>" + close + "</td> </tr>";
            body += content;
            i++;
        }
        try {
            resp.getWriter().println(head + body + tail);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String HTMLFindMoreStockForm = "<form action =\"/home\" method = \"get\">\n" +
                "    " +
                "<input type=\"submit\" value=\"Find more stock history\" name=\"homeRedirect\">\n" +
                "    <" +
                "br>\n" +
                "</form>\n" +
                "\n";
        resp.getWriter().println(HTMLFindMoreStockForm);

        //language=HTML
        String HTMLStockSortForm = "<Html>\n" +
                "<Head>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<p>Select how you want to print out</p>\n" +
                "<p>If you want to sort by name ascending type nameasc or namedesc for descending</p>\n" +
                "<p>If you want to sort by time ascending type timeasc or timedesc for descending</p>\n" +
                "<form action='/printout' method='get'>\n" +
                "    <div class=\"form-example\">\n" +
                "        <label for=\"nameTime\">Type in this box: </label>\n" +
                "        <input type=\"text\" name=\"nameTime\" id=\"nameTime\" required>\n" +
                "    </div>\n" +
                "    <input type=\"submit\" value=\"Submit\"/>\n" +
                "</form>\n" +
                "</Body>\n" +
                "</Html>";
        resp.getWriter().println(HTMLStockSortForm);

    }

}
