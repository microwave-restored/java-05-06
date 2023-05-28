<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.dstu.db.DbWorker" %>
<html>
<head>
    <title>Данные обновлены</title>
</head>
<body>
    Данные обновлены, новая оценка выставлена!
    <h1>Список студентов</h1>
    <%= DbWorker.exportData() %>
</body>
</html>
