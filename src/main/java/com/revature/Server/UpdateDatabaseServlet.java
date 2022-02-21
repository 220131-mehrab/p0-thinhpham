package com.revature.Server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UpdateDatabaseServlet extends HttpServlet {
//    DELETE FROM MYSTOCK  t1
//    WHERE t1.id > (SELECT MIN(t2.id) FROM MYSTOCK t2 WHERE t1.time = t2.time  and t1.name = t2.name);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
