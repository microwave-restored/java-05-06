package org.dstu.db;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class DbWorker {
    public static void doQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Connection conn = DbConnection.getConnection();
        if (conn != null) {
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

    public static String exportData() {
        Connection conn = DbConnection.getConnection();
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table>");
        htmlTable.append("<tr><th>Имя</th><th>Фамилия</th><th>Оценка</th></tr>");
        if (conn != null) {
            try {
                String selectSql = "SELECT * FROM students";
                PreparedStatement selectStatement = conn.prepareStatement(selectSql);
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    int mark = resultSet.getInt("mark");
                    htmlTable.append("<tr>");
                    htmlTable.append("<td>").append(firstName).append("</td>");
                    htmlTable.append("<td>").append(lastName).append("</td>");
                    htmlTable.append("<td>").append(mark).append("</td>");
                    htmlTable.append("</tr>");
                }
                htmlTable.append("</table>");
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                htmlTable = new StringBuilder("Произошла ошибка при выполнении операции");
            }
        }
        htmlTable.append("</table>");
        return htmlTable.toString();
    }
}
