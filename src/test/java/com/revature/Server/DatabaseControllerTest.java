package com.revature.Server;

import com.revature.YahooApi.YahooStockAPI;
import com.revature.domain.MyStock;
import com.revature.domain.StockCommands;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

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

    }


}