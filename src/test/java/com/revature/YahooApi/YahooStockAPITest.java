package com.revature.YahooApi;

import com.revature.domain.MyStock;
import com.revature.domain.StockCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class YahooStockAPITest {
    private YahooStockAPI yahooStockAPI = new YahooStockAPI();
    StockCommands stockCommands = new StockCommands("AAPL", "2019-12-01", "2021-11-01", "monthly");
    List<MyStock> stockList = this.yahooStockAPI.getHistory(stockCommands.getTicker(),
            stockCommands.getStartDate(), stockCommands.getEndDate(), stockCommands.getInterval());

    @Test
    void getHistory() {
        BigDecimal open1 = new BigDecimal("66.817497");
        BigDecimal open2 = new BigDecimal("74.059998");
        BigDecimal close3 = new BigDecimal("68.339996");
        BigDecimal close4 = new BigDecimal("63.572498");
        Assertions.assertEquals(open1, stockList.get(0).getOpen());
        Assertions.assertEquals(open2, stockList.get(1).getOpen());
        Assertions.assertEquals(close3, stockList.get(2).getClose());
        Assertions.assertEquals(close4, stockList.get(3).getClose());
    }


    @Test
    void convertDate() {
//        System.out.println();
        Assertions.assertEquals("2020-01-01", stockList.get(1).getDate());
        Calendar testDate = this.yahooStockAPI.convertDate("2020-01-01");
        Assertions.assertEquals(this.yahooStockAPI.convertDate(stockList.get(1).getDate()), testDate);
    }

    @Test
    void getInterval() {
        Assertions.assertEquals(Interval.MONTHLY, this.yahooStockAPI.getInterval("monthly"));
        Assertions.assertEquals(Interval.DAILY, this.yahooStockAPI.getInterval("daily"));
        Assertions.assertEquals(Interval.WEEKLY, this.yahooStockAPI.getInterval("weekly"));
    }
}