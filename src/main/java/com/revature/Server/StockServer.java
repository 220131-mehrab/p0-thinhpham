package com.revature.Server;

import com.revature.yahoo.stock.api.StockCommands;
import com.revature.yahoo.stock.api.YahooStockAPI;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StockServer {
    private Tomcat server;
    private InputStockService inputStockService;
    private ReceiveInputServlet receiveInputServlet;



//    private FileRepository fileRepository;

    public StockServer() {
        this.server = new Tomcat();
        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test","sa","");
            String query = "DROP TABLE IF EXISTS mystock;";
            String query2 = "CREATE TABLE mystock(ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, NAME VARCHAR(255), " +
                    "TIME DATE, OPEN DECIMAL(15), HIGH DECIMAL(15), LOW DECIMAL(15), CLOSE DECIMAL(15));";
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.execute(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.inputStockService = new InputStockService();
        this.receiveInputServlet = new ReceiveInputServlet();

        this.server.getConnector();
        this.server.addContext("", null);
        this.server.addServlet("", "Input Stock Servlet", this.inputStockService).addMapping("/home");
        this.server.addServlet("", "Receive InputStock Servlet", this.receiveInputServlet).addMapping("/receive");
        this.server.addServlet("", "fddgs", new testClassSerlet()).addMapping("/myTest");


//        this.stockCommands.toString();

        try {
            this.server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }

//    public StockCommands getStockCommands() {
//        return stockCommands;
//    }
}
