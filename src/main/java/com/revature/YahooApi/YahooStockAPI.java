package com.revature.YahooApi;

import com.revature.domain.MyStock;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class YahooStockAPI {
    private Stock myStock;

    /**
     * @param stockName
     * @param from
     * @param to
     * @param searchType
     * @return
     */
    public List<MyStock> getHistory(String stockName, String from, String to, String searchType) {
        Calendar fromDate = convertDate(from);
        Calendar toDate = convertDate(to);
        List<HistoricalQuote> history = null;
        List<MyStock> stocks = new ArrayList<>();
        try {
            Stock stock = YahooFinance.get(stockName);

            //The getHistory function of stock variable is not a recursive call but instead the Yahoo API function
            history = stock.getHistory(fromDate, toDate, getInterval(searchType));
            history.forEach(quote -> {
//                System.out.println("Symbol: " + quote.getSymbol());
//                System.out.println("Date: " + convertDate(quote.getDate()));
//                System.out.println("High Price: " + quote.getHigh());
//                System.out.println("Low Price: " + quote.getLow());
//                System.out.println("Closed Price: " + quote.getClose());
//                System.out.println("=============================");
                stocks.add(new MyStock(quote.getSymbol(), convertDate(quote.getDate()), quote.getOpen(), quote.getHigh(), quote.getLow(), quote.getClose()));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocks;
    }

//    public String WriteToFile(String stockName, String from, String to, String searchType) {
//        BufferedWriter output = null;
//        List<HistoricalQuote> history;
//        String fileSource = "src/main/resources/result.csv";
//        System.out.println(fileSource);
//        try {
//            File file = new File(fileSource);
//            output = new BufferedWriter(new FileWriter(file));
//            history = getHistory(stockName, from, to, searchType);
//            output.write("Symbol, Date, High Price, Low Price, Closed Price\n");
//            for (HistoricalQuote quote : history) {
//                output.write(quote.getSymbol() + ",");
//                output.write(convertDate(quote.getDate()) + ",");
//                output.write(quote.getHigh() + ",");
//                output.write(quote.getLow() + ",");
//                output.write(quote.getClose() + "\n");
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (output != null) {
//                try {
//                    output.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return "result.csv";
//    }

    /**
     * @param cal
     * @return
     */
    public String convertDate(Calendar cal) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(cal.getTime());
    }

    /**
     * @param cal
     * @return
     */
    public Calendar convertDate(String cal) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            calendar.setTime(sdf.parse(cal));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * @param searchType
     * @return
     */
    public Interval getInterval(String searchType) {
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