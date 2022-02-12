package com.revature.yahoo.stock.api;

import com.revature.yahoo.stock.api.dto.StockDto;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
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

    public List<HistoricalQuote> getHistory(String stockName) {
        try {
            Stock stock = YahooFinance.get(stockName);
            List<HistoricalQuote> history = stock.getHistory();
            for (HistoricalQuote quote : history) {
                System.out.println("=============================");
                System.out.println("Symbol: " + quote.getSymbol());
                System.out.println("Date: " + quote.getDate());
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

    public static void main(String[] args) {
        YahooStockAPI yahooStockAPI = new YahooStockAPI();
        System.out.println(yahooStockAPI.getStock("AAPL"));

        yahooStockAPI.getHistory("PLTR");
    }
}
