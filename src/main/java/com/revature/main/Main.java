package com.revature.main;

import com.revature.yahoo.stock.api.YahooStockAPI;

public class Main {
    public static void main(String[] args) {
        YahooStockAPI yahooStockAPI = new YahooStockAPI();
        System.out.println(yahooStockAPI.getStock("AAPL"));

        yahooStockAPI.getHistory("PLTR", 1, "monthly");
    }
}
