package com.revature.Server;

import com.revature.yahoo.stock.api.MyStock;
import com.revature.yahoo.stock.api.StockCommands;
import com.revature.yahoo.stock.api.YahooStockAPI;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReceiveInputServlet extends HttpServlet {

    private StockCommands stockCommands;
    private YahooStockAPI yahooStockAPI;

    public ReceiveInputServlet() {
        this.yahooStockAPI = new YahooStockAPI();
    }

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

        stockCommands = new StockCommands(ticker, startDate, endDate, interval);
        List<MyStock> stockList = this.yahooStockAPI.getHistory(this.stockCommands.getTicker(),
                this.stockCommands.getStartDate(), this.stockCommands.getEndDate(), this.stockCommands.getInterval());

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();

            for (MyStock index : stockList) {
                String name = index.getName();
                String time = index.getDate();
                BigDecimal open = index.getOpen();
                BigDecimal high = index.getHigh();
                BigDecimal low = index.getLow();
                BigDecimal close = index.getClose();
//                String query = "INSERT INTO mystock VALUES('" + name + "', '" + date + "', " + open + ", " + high + ", " + low + ", " + close + ");";
                String query = "insert into mystock (name, time, open, high, low, close) values ('" + name + "', '" + time + "', " + open + ", " + high + ", " + low + ", " + close + ");";
                System.out.println(query);
                statement.execute(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        String HTMLForm = "<p>A csv file has been generated</p>\n" +
//                "<form action='/myTest' method='get'>\n" +
//                "    " +
//                "    <label></label>\n" +
//                "</form>";

    }

    public StockCommands getStockCommands() {
        return stockCommands;
    }
}
