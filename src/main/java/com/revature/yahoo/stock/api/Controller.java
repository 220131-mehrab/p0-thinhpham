package com.revature.yahoo.stock.api;

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
                case 8:
                    isExit = true;
            }
        } while (!isExit);

    }

    public void Menu() {
        StringBuilder menu = new StringBuilder("\tWelcome to the Stock History App\n")
                .append("1. Select 1 to reprint menu\n")
                .append("2. Select 2 to watch the most recent stock\n")
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

}
