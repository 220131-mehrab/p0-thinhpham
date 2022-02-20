package com.revature.Server;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.fileRepository = new FileRepository("result.csv");
//        this.stockLine = new ArrayList<>();
//        this.file = getClass().getClassLoader().getResourceAsStream("result.csv");
//        Scanner scanner = new Scanner(this.file);
//        scanner.useDelimiter("\n");
//        while (scanner.hasNext()) {
//            this.stockLine.add(scanner.next());
//        }
//        for (String stock : this.stockLine) {
//            resp.getWriter().println(stock);
//        }
        String query = "SELECT * FROM MYSTOCK;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            resp.getWriter().println("\tSymbol \t Date \t Open Price \t High Price \t Low Price \t Closed Price");
            while (rs.next()) {
//                System.out.println(rs.getString(1) + ", " + rs.getString(2)+ ", " + rs.getString(3));
                resp.getWriter().println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
                        + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5)
                        + "\t" + rs.getString(6) + "\t" + rs.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
