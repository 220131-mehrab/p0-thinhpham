package com.revature.main;

import com.revature.Server.StockServer;
import com.revature.domain.MyStock;
import com.revature.YahooApi.YahooStockAPI;

/**
 *
 */
public class Controller {

    public Controller() {
        StartServer();
    }

    private void StartServer() {
        StockServer stockServer = new StockServer();
    }


}
