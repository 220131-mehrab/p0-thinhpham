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

    private void StartServer() {
//        System.out.print("\nEnter the name of the file: ");
//        String fileName = new Scanner(System.in).nextLine();
//        String fileSource = fileName + ".csv";

//        FileRepository fileRepository = new FileRepository();
//        InputStockService stockService = new InputStockService();
//        StockCommands stockCommands = stockService.getStockCommands();
//        this.yahooStockAPI.getHistory(stockCommands.getTicker(), stockCommands.getStartDate(),
//                stockCommands.getEndDate(), stockCommands.getInterval());
//        this.yahooStockAPI.WriteToFile(stockCommands.getTicker(), stockCommands.getStartDate(),
//                stockCommands.getEndDate(), stockCommands.getInterval());

        StockServer stockServer = new StockServer();
//        StockCommands stockCommands = stockServer.getStockCommands();
//        stockCommands.toString();
    }


}
