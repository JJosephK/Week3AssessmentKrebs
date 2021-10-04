<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Editing</title>
</head>
<body>
<form action = "editItemServlet" method = "post">
Type: <input type = "text" name = "type" value = "${itemToEdit.type}">
Item: <input type = "text" name = "item" value = "${itemToEdit.item}">
<input type = "hidden" name = "id" value = "${itemToEdit.id}">
<input type = "submit" value = "Save Edited Item">
</form>
</body>
</html>