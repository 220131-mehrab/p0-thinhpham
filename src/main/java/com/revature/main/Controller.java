package com.revature.main;

import com.revature.yahoo.stock.api.YahooStockAPI;

import java.util.Calendar;
import java.util.Scanner;

public class Controller {

    private YahooStockAPI yahooStockAPI;

    public Controller() {
        this.yahooStockAPI = new YahooStockAPI();
        Start();
    }

    private void Start() {
        int selection;
        boolean isExit = false;

        do {
//            String dateS = "2021-07-17";
//            Calendar date = this.yahooStockAPI.convertDate(dateS);
//            System.out.println(date);
//            this.yahooStockAPI.getHistory("AAPL", 2021, "monthly");
            Menu();
            selection = new Scanner(System.in).nextInt();
            switch (selection) {
                case 1:
                    Menu();
                    break;
                case 2:
                    String ticker = TickerInput();
                    System.out.println(this.yahooStockAPI.getStock(ticker));
                    break;
                case 3:
                    String ticker1 = TickerInput();
                    System.out.print("Please enter your start date ");
                    String from = DateInput();
                    System.out.print("\n Please enter your end date ");
                    String to = DateInput();
                    String interval = IntervalInput();
                    System.out.println("\n");
                    this.yahooStockAPI.getHistory(ticker1, from, to, interval);
                    this.yahooStockAPI.WriteToFile(ticker1, from, to, interval);
                    break;
                case 8:
                    isExit = true;
            }
        } while (!isExit);

    }

    public void Menu() {
        StringBuilder menu = new StringBuilder("\tWelcome to the Stock History App\n")
                .append("1. Select 1 to reprint menu\n")
                .append("2. Select 2 to watch the most recent stock\n")
                .append("3. Select 3 to watch history of stock with an amount of time\n")
                .append("4. Select 4 to write to a csv file\n")
                .append("8. Select 8 to exit\n")
                .append("Please enter: ");
        System.out.print(menu);
    }

    private String TickerInput() {
        String ticker;
        System.out.print("Please enter your stock ticker: ");
        ticker = new Scanner(System.in).nextLine();
        return ticker;
    }

    private String DateInput() {
        String date;
        System.out.print(" (yyyy-mm-dd): ");
        date = new Scanner(System.in).nextLine();
        return date;
    }

    private String IntervalInput() {
        String interval;
        System.out.print("\nEnter 'Yearly' 'Monthly' or 'Daily' : ");
        interval = new Scanner(System.in).nextLine();
        return interval;
    }


}
