package com.revature.yahoo.stock.api;

public class StockCommands {
    private String ticker;
    private String startDate;
    private String endDate;
    private String interval;

    public StockCommands(String ticker, String startDate, String endDate, String interval) {
        this.ticker = ticker;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
    }

    public String getTicker() {
        return ticker;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return "StockCommands{" +
                "ticker='" + ticker + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", interval='" + interval + '\'' +
                '}';
    }
}
