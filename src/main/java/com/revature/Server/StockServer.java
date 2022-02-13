package com.revature.Server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class StockServer {
    private Tomcat server;

    public StockServer(StockService stockService) {
        this.server = new Tomcat();
        this.server.getConnector();
        this.server.addContext("",null);
        this.server.addServlet("","Stock Servlet", stockService).addMapping("/");
        try {
            this.server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
