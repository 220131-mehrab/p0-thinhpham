package com.revature.yahoo.stock.api;

import com.revature.yahoo.stock.api.dto.StockDto;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class YahooStockAPI {
    public StockDto getStock(String stockTicker) {
        StockDto dto = null;
        try {
            Stock stock = YahooFinance.get(stockTicker);
            dto = new StockDto(stock.getName(), stock.getQuote().getPrice(), stock.getQuote().getChange(),
                    stock.getCurrency(), stock.getQuote().getBid());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }
// This method would be preserved to do the dividend history
//    public List<HistoricalQuote> getHistory(String stockName, int year, String searchType) {
//        Calendar from = Calendar.getInstance();
//        Calendar to = Calendar.getInstance();
//        from.add(Calendar.YEAR, Integer.valueOf("-" + year));
//        try {
//            Stock stock = YahooFinance.get(stockName);
//            List<HistoricalDividend> quote = stock.getDividendHistory();
//            quote.forEach((HistoricalDividend s) -> {
//                System.out.print(s.getAdjDividend());
//                System.out.print(" ");
//                System.out.println(convertDate(s.getDate()));
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<HistoricalQuote> getHistory(String stockName, String from, String to, String searchType) {
        Calendar fromDate = convertDate(from);
        Calendar toDate = convertDate(to);
        List<HistoricalQuote> history = null;
        try {
            Stock stock = YahooFinance.get(stockName);
            //The getHistory function of stock variable is not a recursive call but instead the Yahoo API function
            history = stock.getHistory(fromDate, toDate, getInterval(searchType));
            history.forEach(quote -> {
                System.out.println("Symbol: " + quote.getSymbol());
                System.out.println("Date: " + convertDate(quote.getDate()));
                System.out.println("High Price: " + quote.getHigh());
                System.out.println("Low Price: " + quote.getLow());
                System.out.println("Closed Price: " + quote.getClose());
                System.out.println("=============================");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return history;
    }

    public void WriteToFile(String stockName, String from, String to, String searchType) {
        BufferedWriter output = null;
        List<HistoricalQuote> history;
        System.out.print("\nEnter the name of the file you want to write to: ");
        String fileName = new Scanner(System.in).nextLine();
        String fileSource = "src/main/resources/" + fileName + ".csv";
        System.out.println(fileSource);
        try {
            File file = new File(fileSource);
            output = new BufferedWriter(new FileWriter(file));
            history = getHistory(stockName, from, to, searchType);
            output.write("Symbol, Date, High Price, Low Price, Closed Price\n");
            for (HistoricalQuote quote : history) {
                output.write(quote.getSymbol() + ",");
                output.write(convertDate(quote.getDate()) + ",");
                output.write(quote.getHigh() + ",");
                output.write(quote.getLow() + ",");
                output.write(quote.getClose() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String convertDate(Calendar cal) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(cal.getTime());
    }

    private Calendar convertDate(String cal) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            calendar.setTime(sdf.parse(cal));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
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

}
