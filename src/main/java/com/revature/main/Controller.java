package com.revature.main;

import com.revature.Server.StockServer;
import com.revature.Server.InputStockService;
import com.revature.yahoo.stock.api.MyStock;
import com.revature.yahoo.stock.api.StockCommands;
import com.revature.yahoo.stock.api.YahooStockAPI;

public class Controller {

    private final YahooStockAPI yahooStockAPI;
    private MyStock myStock;

    public Controller() {
        this.yahooStockAPI = new YahooStockAPI();
        StartServer();
    }

//    private void Start() {
//        int selection;
//        boolean isExit = false;
//
//        do {
//            Menu();
//            selection = new Scanner(System.in).nextInt();
//            switch (selection) {
//                case 1:
////                    String ticker = TickerInput();
////                    System.out.println(this.yahooStockAPI.getStock(ticker));
//                    break;
//                case 2:
//                    StockDataController();
//                    break;
//                case 3:
//                    PrintToServer();
//                    break;
//                case 8:
//                    isExit = true;
//            }
//        } while (!isExit);
//
//    }

//    public void Menu() {
//        StringBuilder menu = new StringBuilder("\tWelcome to the Stock History App\n")
//                .append("1. Select 1 to watch the most recent stock\n")
//                .append("2. Select 2 to watch history of stock with an amount of time\n")
//                .append("3. Select 3 to print to website\n")
//                .append("8. Select 8 to exit\n")
//                .append("Please enter: ");
//        System.out.print(menu);
//    }
//
//    private String TickerInput() {
//        String ticker;
//        System.out.print("Please enter your stock ticker: ");
//        ticker = new Scanner(System.in).nextLine();
//        return ticker;
//    }
//
//    private String DateInput() {
//        String date;
//        System.out.print(" (yyyy-mm-dd): ");
//        date = new Scanner(System.in).nextLine();
//        return date;
//    }
//
//    private String IntervalInput() {
//        String interval;
//        System.out.print("\nEnter 'Yearly' 'Monthly' or 'Daily' : ");
//        interval = new Scanner(System.in).nextLine();
//        return interval;
//    }

//    private void StockDataController() {
//        String ticker1 = TickerInput();
//        System.out.print("Please enter your start date ");
//        String from = DateInput();
//        System.out.print("\n Please enter your end date ");
//        String to = DateInput();
//        String interval = IntervalInput();
//        System.out.println("\n");
//        this.yahooStockAPI.getHistory(ticker1, from, to, interval);
//        System.out.print("Do you want to save this data? Choose Y for yes or N for no");
//        String selection = new Scanner(System.in).nextLine().toLowerCase();
//        if (selection.equals("y")) {
//            this.yahooStockAPI.WriteToFile(ticker1, from, to, interval);
//        }
//    }

    private void StartServer() {
//        System.out.print("\nEnter the name of the file: ");
//        String fileName = new Scanner(System.in).nextLine();
//        String fileSource = fileName + ".csv";

//        FileRepository fileRepository = new FileRepository();
        InputStockService stockService = new InputStockService();
//        StockCommands stockCommands = stockService.getStockCommands();
//        this.yahooStockAPI.getHistory(stockCommands.getTicker(), stockCommands.getStartDate(),
//                stockCommands.getEndDate(), stockCommands.getInterval());
//        this.yahooStockAPI.WriteToFile(stockCommands.getTicker(), stockCommands.getStartDate(),
//                stockCommands.getEndDate(), stockCommands.getInterval());

        StockServer stockServer = new StockServer();
    }


}
