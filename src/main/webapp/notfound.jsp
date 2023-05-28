<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.dstu.db.DbWorker" %>
<html>
<head>
    <title>Студент не найден</title>
</head>
<body>
    Такого студента не существует!
    <h1>Список студентов</h1>
    <%= DbWorker.exportData() %>
</body>
</html>
