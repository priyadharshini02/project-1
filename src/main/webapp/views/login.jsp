<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<h2 style="color:MediumSeaGreen;">LOGIN</h2>
<form action="/submitlogin">
  <label for="email">email:</label>
  <input type="text" id="email" name="email"><br>
  <label for="password">password:</label><br>
  <input type="text" id="password" name="password" ><br><br>
  <input type="submit" value="Submit">
</form>  

<br>
<form action="/register">
 
  <input type="submit" value="register">
</form>
<p style="color:red;">${error}</p>
</body>
</html>