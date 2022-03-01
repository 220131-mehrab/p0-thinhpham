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
        Calendar fromDate = this.yahooStockAPI.convertDate("2019-12-01");
        Calendar toDate = this.yahooStockAPI.convertDate("2021-11-01");
        List<HistoricalQuote> history = null;
        List<MyStock> stocks = new ArrayList<>();
        Stock stock = null;
        try {
            stock = YahooFinance.get("AAPL");
            history = stock.getHistory(fromDate, toDate, this.yahooStockAPI.getInterval("monthly"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //The getHistory function of stock variable is not a recursive call but instead the Yahoo API function


    }


    @Test
    void convertDate() {
//        System.out.println();
        Assertions.assertEquals("2020-01-01", stockList.get(1).getDate());
    }

    @Test
    void testConvertDate() {
    }

    @Test
    void getInterval() {
        Assertions.assertEquals(Interval.MONTHLY, this.yahooStockAPI.getInterval("monthly"));
        Assertions.assertEquals(Interval.DAILY, this.yahooStockAPI.getInterval("daily"));
        Assertions.assertEquals(Interval.WEEKLY, this.yahooStockAPI.getInterval("weekly"));
    }
}