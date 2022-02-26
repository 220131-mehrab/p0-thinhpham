package com.revature.Server;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
//    DELETE FROM MYSTOCK  t1
//    WHERE t1.id > (SELECT MIN(t2.id) FROM MYSTOCK t2 WHERE t1.time = t2.time  and t1.name = t2.name);

    private Connection connection;
    private Statement statement;
    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS mystock;";
    private final String CREATE_TABLE_QUERY = "CREATE TABLE mystock(ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, NAME VARCHAR(255), " +
            "TIME DATE, OPEN DECIMAL(15,6), HIGH DECIMAL(15,6), LOW DECIMAL(15,6), CLOSE DECIMAL(15,6));";

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

    public void InsertData(String name, String time, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close) {
        String query = "insert into mystock (name, time, open, high, low, close) values ('" + name + "', '" + time + "', " + open + ", " + high + ", " + low + ", " + close + ");";
//        System.out.println(query);
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            this.statement = this.connection.createStatement();
            this.statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CleanUpData() {

    }
}
