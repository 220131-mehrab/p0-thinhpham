package com.revature.Server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class StockServer {
    private Tomcat server;
//    private FileRepository fileRepository;

    public StockServer() {
        this.server = new Tomcat();

        this.server.getConnector();
        this.server.addContext("", null);
        this.server.addServlet("","Input Stock Servlet", new InputStockService()).addMapping("/home");
        this.server.addServlet("","Receive InputStock Servlet", new ReceiveInputServlet()).addMapping("/receive");
        this.server.addServlet("", "fddgs", new testClassSerlet()).addMapping("/myTest");
        try {
            this.server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
