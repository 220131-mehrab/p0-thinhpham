package com.revature.yahoo.stock.api;

import com.revature.yahoo.stock.api.dto.StockDto;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class YahooStockAPI {
    public StockDto getStock(String stockTicker) {
        StockDto dto = null;
        try {
            Stock stock = YahooFinance.get(stockTicker);
            dto = new StockDto(stock.getName(), stock.getQuote().getPrice(), stock.getQuote().getChange(), stock.getCurrency(), stock.getQuote().getBid());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<HistoricalQuote> getHistory(String stockName, int year, String searchType) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, Integer.valueOf("-" + year));
        try {
            Stock stock = YahooFinance.get(stockName);
            List<HistoricalQuote> history = stock.getHistory(from, to, getInterval(searchType));
            for (HistoricalQuote quote : history) {

                System.out.println("Symbol: " + quote.getSymbol());
                System.out.println("Date: " + convertDate(quote.getDate()));
                System.out.println("High Price: " + quote.getHigh());
                System.out.println("Low Price: " + quote.getLow());
                System.out.println("Closed Price: " + quote.getClose());
                System.out.println("=============================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertDate(Calendar cal) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = format.format(cal.getTime());
        return formattedDate;
    }

    private Interval getInterval(String searchType) {
        Interval interval = null;
        switch (searchType.toUpperCase()) {
            case "MONTHLY":
                interval = Interval.MONTHLY;
                break;
            case "WEEKLY":
                interval = Interval.WEEKLY;
                break;
            case "DAILY":
                interval = Interval.DAILY;

        }
        return interval;
    }

    public static void main(String[] args) {
        YahooStockAPI yahooStockAPI = new YahooStockAPI();
        System.out.println(yahooStockAPI.getStock("AAPL"));

        yahooStockAPI.getHistory("PLTR", 1, "monthly");
    }
}