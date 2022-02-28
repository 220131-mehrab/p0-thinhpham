package com.revature.Servlet;

import com.revature.Server.DatabaseController;
import com.revature.domain.MyStock;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;


public class PrintOutServlet extends HttpServlet {
    //    private FileRepository fileRepository;
    private List<String> stockLine;
    private InputStream file;

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String query = "SELECT * FROM MYSTOCK;";
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
//            Statement statement = connection.createStatement();
//
//            ResultSet rs = statement.executeQuery(query);
        String HTMLTitle = "<h3" +
                ">Symbol, Date, Open Price, High Price, Low Price, Closed Price</h3>";
//
        resp.getWriter().println(HTMLTitle);
//            while (rs.next()) {
//                String HTMLContent = rs.getString(1) + "\t " + rs.getString(2) + "\t, "
//                        + rs.getString(3) + "\t, " + rs.getString(4) + "\t, " + rs.getString(5)
//                        + "\t, " + rs.getString(6) + "\t, " + rs.getString(7) + ".";
//                String convertContent = "<p>" + HTMLContent + "</p>" + "\n";
//                resp.getWriter().println(convertContent);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        List<MyStock> stockList = new DatabaseController().PrintOut("nameasc");
        stockList.forEach(myStock -> {
            String HTMLContent = myStock.getName() + "\t " + myStock.getDate() + "\t, "
                    + myStock.getOpen() + "\t, " + myStock.getHigh() + "\t, " + myStock.getLow()
                    + "\t, " + myStock.getClose() + ".";
            String convertContent = "<p>" + HTMLContent + "</p>" + "\n";
            try {
                resp.getWriter().println(convertContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        String HTMLFindMoreStockForm = "<Html>\n" +
                "<Head>\n" +
                "    <Title>Stock History</Title>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<form action=\"/home\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Find more stock history\" name=\"homeRedirect\">\n" +
                "    <br" +
                ">\n" +
                "</form>\n" +
                "</Body>\n" +
                "</Html>\n" +
                "\n" +
                "\n";
        resp.getWriter().println(HTMLFindMoreStockForm);
    }
}
