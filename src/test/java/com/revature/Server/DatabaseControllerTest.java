package com.revature.Server;

import com.revature.YahooApi.YahooStockAPI;
import com.revature.domain.MyStock;
import com.revature.domain.StockCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class DatabaseControllerTest {
    private DatabaseController databaseController = new DatabaseController();

    @Test
    void insertData() {
        this.databaseController.CreateTable();
        YahooStockAPI yahooStockAPI = new YahooStockAPI();
        StockCommands stockCommands = new StockCommands("AAPL", "2019-12-01", "2021-11-01", "monthly");
        List<MyStock> stockList = yahooStockAPI.getHistory(stockCommands.getTicker(),
                stockCommands.getStartDate(), stockCommands.getEndDate(), stockCommands.getInterval());
        for (MyStock index : stockList) {
            String name = index.getName();
            String time = index.getDate();
            BigDecimal open = index.getOpen();
            BigDecimal high = index.getHigh();
            BigDecimal low = index.getLow();
            BigDecimal close = index.getClose();
            this.databaseController.InsertData(name, time, open, high, low, close);
        }
        List<MyStock> myStockList = this.databaseController.PrintOutBySort("timeasc");
        Assertions.assertEquals("2019-12-01", myStockList.get(0).getDate());
        Assertions.assertEquals("2020-01-01", myStockList.get(1).getDate());
        Assertions.assertEquals("AAPL", myStockList.get(2).getName());
        Assertions.assertEquals("AAPL", myStockList.get(11).getName());
        Assertions.assertEquals(new BigDecimal("77.377502"), myStockList.get(1).getClose());

    }


    @Test
    void printOutBySort() {
        this.databaseController.CreateTable();
        YahooStockAPI yahooStockAPI = new YahooStockAPI();
        StockCommands stockCommands = new StockCommands("AAPL", "2019-12-01", "2021-11-01", "monthly");
        List<MyStock> stockList = yahooStockAPI.getHistory(stockCommands.getTicker(),
                stockCommands.getStartDate(), stockCommands.getEndDate(), stockCommands.getInterval());
        for (MyStock index : stockList) {
            String name = index.getName();
            String time = index.getDate();
            BigDecimal open = index.getOpen();
            BigDecimal high = index.getHigh();
            BigDecimal low = index.getLow();
            BigDecimal close = index.getClose();
            this.databaseController.InsertData(name, time, open, high, low, close);
        }
        List<MyStock> timeAscList = this.databaseController.PrintOutBySort("timeasc");
        Assertions.assertEquals("2019-12-01", timeAscList.get(0).getDate());
        Assertions.assertEquals("2021-11-01", timeAscList.get(timeAscList.size()-1).getDate());
        List<MyStock> timeDescList = this.databaseController.PrintOutBySort("timedesc");
        Assertions.assertEquals("2021-11-01", timeDescList.get(0).getDate());
        Assertions.assertEquals("2019-12-01", timeDescList.get(timeDescList.size()-1).getDate());
        StockCommands stockCommands2 = new StockCommands("IBM", "2018-12-01", "2021-11-01", "monthly");
        List<MyStock> stockList2 = yahooStockAPI.getHistory(stockCommands2.getTicker(),
                stockCommands2.getStartDate(), stockCommands2.getEndDate(), stockCommands2.getInterval());
        for (MyStock index : stockList2) {
            String name = index.getName();
            String time = index.getDate();
            BigDecimal open = index.getOpen();
            BigDecimal high = index.getHigh();
            BigDecimal low = index.getLow();
            BigDecimal close = index.getClose();
            this.databaseController.InsertData(name, time, open, high, low, close);
        }
        List<MyStock> nameAscList = this.databaseController.PrintOutBySort("nameasc");
        Assertions.assertEquals("AAPL",nameAscList.get(0).getName());
        Assertions.assertEquals("IBM", nameAscList.get(nameAscList.size()-1).getName());
        List<MyStock> nameDescList = this.databaseController.PrintOutBySort("namedesc");
        Assertions.assertEquals("IBM",nameDescList.get(0).getName());
        Assertions.assertEquals("AAPL", nameDescList.get(nameDescList.size()-1).getName());
    }

    @Test
    void printOutByTime() {
        this.databaseController.CreateTable();
        YahooStockAPI yahooStockAPI = new YahooStockAPI();
        StockCommands stockCommands = new StockCommands("AAPL", "2019-12-01", "2021-11-01", "monthly");
        List<MyStock> stockList = yahooStockAPI.getHistory(stockCommands.getTicker(),
                stockCommands.getStartDate(), stockCommands.getEndDate(), stockCommands.getInterval());
        for (MyStock index : stockList) {
            String name = index.getName();
            String time = index.getDate();
            BigDecimal open = index.getOpen();
            BigDecimal high = index.getHigh();
            BigDecimal low = index.getLow();
            BigDecimal close = index.getClose();
            this.databaseController.InsertData(name, time, open, high, low, close);
        }
        List<MyStock> timeList = this.databaseController.PrintOutByTime("2020-01-01","2020-05-01");
        Assertions.assertEquals("2020-01-01", timeList.get(0).getDate());
        Assertions.assertEquals("2020-05-01", timeList.get(timeList.size()-1).getDate());
        Assertions.assertEquals(5, timeList.size());
    }
}