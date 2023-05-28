<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.dstu.db.DbWorker" %>
<html>
<head>
    <title>Student</title>
</head>
<body>
    <form action="students" method="GET">
        Фамилия: <input type="text" value="" name="lastName"> <br>
        Имя: <input type="text" value="" name="firstName"> <br>
        Отчество: <input type="text" value="" name="middleName"> <br>
        Дисциплина: <input type="text" value="" name="programme"> <br>
        Оценка: <input type="number" value="" name="mark"> <br>
        <button type="submit">Добавить</button> <br>
    </form>
    <h1>Список студентов</h1>
    <%= DbWorker.exportData() %>
</body>
</html>
