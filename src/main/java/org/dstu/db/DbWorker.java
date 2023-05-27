package org.dstu.db;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbWorker {
    public static void doQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Connection conn = DbConnection.getConnection();
        String[] lastName = req.getParameterValues("lastName");
        String[] mark = req.getParameterValues("mark");
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM students WHERE last_name = ?");
            PreparedStatement stMark = conn.prepareStatement("SELECT mark FROM students WHERE last_name = ?");
            statement.setString(1, lastName[0]);
            stMark.setString(1, lastName[0]);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    ResultSet resultSet2 = stMark.executeQuery();
                    if (resultSet2.next()) {
                        int studMark = resultSet2.getInt("mark");
                        if (studMark > 2) {
                            req.getRequestDispatcher("positivemark.jsp").forward(req, resp);
                        } else {
                            String updateSql = "UPDATE students SET mark = ? WHERE last_name = ?";
                            PreparedStatement upd = conn.prepareStatement(updateSql);
                            upd.setInt(1, Integer.parseInt(mark[0]));
                            upd.setString(2, lastName[0]);
                            upd.executeUpdate();
                            req.getRequestDispatcher("updated.jsp").forward(req, resp);
                        }
                    }
                } else {
                    req.getRequestDispatcher("notfound.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
