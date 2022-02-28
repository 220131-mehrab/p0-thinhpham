package com.revature.Server;

import com.revature.Servlet.InputStockService;
import com.revature.Servlet.PrintOutServlet;
import com.revature.Servlet.ReceiveInputServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;


/**
 *
 */
public class StockServer {
    private Tomcat server;
    private InputStockService inputStockService;
    private ReceiveInputServlet receiveInputServlet;

    /**
     *
     */
    public StockServer() {
        this.server = new Tomcat();
        this.inputStockService = new InputStockService();
        this.receiveInputServlet = new ReceiveInputServlet();
        new DatabaseController().CreateTable();

        this.server.getConnector();
        this.server.addContext("", null);
        this.server.addServlet("", "Input Stock Servlet", this.inputStockService).addMapping("/home");
        this.server.addServlet("", "Receive InputStock Servlet", this.receiveInputServlet).addMapping("/receive");
        this.server.addServlet("", "Selection Servlet", this.receiveInputServlet).addMapping("/selection");
        this.server.addServlet("", "Print Data", new PrintOutServlet()).addMapping("/printout");
        try {
            this.server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
