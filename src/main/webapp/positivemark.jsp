<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.dstu.db.DbWorker" %>

<html>
<head>
    <title>Оценка больше двойки</title>
</head>
<body>
    Нельзя пересдавать, если оценка выше двойки.
    <h1>Список студентов</h1>
    <%= DbWorker.exportData() %>
    </body>
</html>
