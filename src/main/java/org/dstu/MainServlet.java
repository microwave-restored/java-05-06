package org.dstu;

import org.dstu.db.DbConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        try (PrintWriter out = resp.getWriter()){
        //            out.println(DbConnection.getConnection());
        //        }
        req.getRequestDispatcher("main.jsp").forward(req, resp);
        resp.setContentType("test/html;charset=UTF-8");
    }
}
