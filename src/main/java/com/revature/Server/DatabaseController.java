package com.revature.Server;

import com.revature.domain.MyStock;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class manages and provides services that relate to database
 */
public class DatabaseController {


    private Connection connection;
    private Statement statement;
    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS mystock;";
    private final String CREATE_TABLE_QUERY = "CREATE TABLE mystock(ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, NAME VARCHAR(255), " +
            "TIME DATE, OPEN DECIMAL(15,6), HIGH DECIMAL(15,6), LOW DECIMAL(15,6), CLOSE DECIMAL(15,6));";
    private final String SELECT_ALL_QUERY = "SELECT * FROM MYSTOCK;";

    /**
     *This method will delete a table MYSTOCK
     * and create a table MYSTOCK with seven columns
     * ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
     * NAME VARCHAR(255)
     * TIME DATE
     * OPEN DECIMAL(15,6)
     * HIGH DECIMAL(15,6)
     * LOW DECIMAL(15,6)
     * CLOSE DECIMAL(15,6)
     */
    public void CreateTable() {
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            this.statement = this.connection.createStatement();
            this.statement.execute(this.DROP_TABLE_QUERY);
            this.statement.execute(this.CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is insert data to table MYSTOCK
     * @param name is the ticker name
     * @param time is the record day (Format: yyyy-mm-dd)
     * @param open is the price when the stock opened
     * @param high is the price when the stock is at the highest
     * @param low is the price when the stock is at the lowest
     * @param close is the price when the stock closed
     */
    public void InsertData(String name, String time, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close) {
        String insertQuery = "insert into mystock (name, time, open, high, low, close) values ('" + name + "', '" +
                time + "', " + open + ", " + high + ", " + low + ", " + close + ");";
        String deleteDuplicateQuery = "DELETE FROM MYSTOCK t1 WHERE t1.id > (SELECT MIN(t2.id) FROM MYSTOCK t2 WHERE t1.time = t2.time  and t1.name = t2.name);";
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            this.statement = this.connection.createStatement();
            //Insert and then delete duplicate
            this.statement.execute(insertQuery);
            this.statement.execute(deleteDuplicateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will create and return an ascending or descending
     * sorted list by name and time according to users.
     * @param rule is a string. Users have four options
     *             nameasc for name ascending order
     *             namedesc for name descending order
     *             timeasc for time ascending (default)
     *             timedesc for time descending
     * @return a list of MyStock objects
     */
    public List<MyStock> PrintOutBySort(String rule) {
        List<MyStock> stockList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();
            ResultSet rs;
            switch (rule.trim()) {
                case "nameasc":
                    rs = statement.executeQuery("select * from mystock order by name asc;");
                    break;
                case "namedesc":
                    rs = statement.executeQuery("select * from mystock order by name desc;");
                    break;
                case "timedesc":
                    rs = statement.executeQuery("select * from mystock order by time desc;");
                    break;
                default:
                    rs = statement.executeQuery("select * from mystock order by time asc;");

            }
            while (rs.next()) {
                String name = rs.getString(2);
                String time = rs.getString(3);
                BigDecimal open = new BigDecimal(rs.getString(4));
                BigDecimal high = new BigDecimal(rs.getString(5));
                BigDecimal low = new BigDecimal(rs.getString(6));
                BigDecimal close = new BigDecimal(rs.getString(7));
                stockList.add(new MyStock(name, time, open, high, low, close));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    /**
     * This method will create and return a list of all the record in a period of time and order by time
     * @param startTime is the starting time
     * @param endTime is the ending time
     * @return a list of MyStock objects
     */
    public List<MyStock> PrintOutByTime(String startTime, String endTime) {
        List<MyStock> stockList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM MYSTOCK WHERE time >= '" + startTime + "' AND time <= '" + endTime + "' ORDER BY time;");
            while (rs.next()) {
                String name = rs.getString(2);
                String time = rs.getString(3);
                BigDecimal open = new BigDecimal(rs.getString(4));
                BigDecimal high = new BigDecimal(rs.getString(5));
                BigDecimal low = new BigDecimal(rs.getString(6));
                BigDecimal close = new BigDecimal(rs.getString(7));
                stockList.add(new MyStock(name, time, open, high, low, close));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }
}
