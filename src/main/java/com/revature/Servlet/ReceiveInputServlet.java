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
//    private DatabaseController databaseController;

    /**
     *
     */
    public ReceiveInputServlet() {
        this.yahooStockAPI = new YahooStockAPI();
//        this.databaseController = new DatabaseController();
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
//        System.out.println("Ticker: " + ticker);
        String startDate = req.getParameter("startDate");
//        System.out.println("Start: " + startDate);
        String endDate = req.getParameter("endDate");
//        System.out.println("End: " + endDate);
        String interval = req.getParameter("interval");
//        System.out.println("Interval: " + interval);
        String HTMLContent = "";
        String convertContent = "";
        stockCommands = new StockCommands(ticker, startDate, endDate, interval);
        List<MyStock> stockList = this.yahooStockAPI.getHistory(this.stockCommands.getTicker(),
                this.stockCommands.getStartDate(), this.stockCommands.getEndDate(), this.stockCommands.getInterval());
        String HTMLTitle = "<h3" +
                ">Symbol, Date, Open Price, High Price, Low Price, Closed Price</h3>";

        resp.getWriter().println(HTMLTitle);
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
//            Statement statement = connection.createStatement();

        for (MyStock index : stockList) {
            String name = index.getName();
            String time = index.getDate();
            BigDecimal open = index.getOpen();
            BigDecimal high = index.getHigh();
            BigDecimal low = index.getLow();
            BigDecimal close = index.getClose();
            System.out.println(open);
//                String query = "insert into mystock (name, time, open, high, low, close) values ('" + name + "', '" + time + "', " + open + ", " + high + ", " + low + ", " + close + ");";
//                statement.execute(query);
//            this.databaseController.InsertData(name, time, open, high, low, close);
            new DatabaseController().InsertData(name, time, open, high, low, close);
            HTMLContent = name + ", " + time + ", " + open + ", " + high + ", " + low + ", " + close + ".";
            convertContent = "<p>" + HTMLContent + "</p>" + "\n";
            resp.getWriter().println(convertContent);
        }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

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
