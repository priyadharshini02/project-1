<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>  
<style>  
.error{color:red}  
</style>  
</head>
<body>
<h2 style="color:MediumSeaGreen;">REGISTER</h2>

<form:form action="submitForm" modelAttribute="userForm" >  
        First name: <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error"/>         
        <br><br>  
        Last name: <form:input path="lastName"/>  
        <br><br>  
        email: <form:input path="email"/> 
        <form:errors path="email" cssClass="error"/> 
        <br><br> 
        phone: <form:input path="phone"/>  
        <br><br> 
        password: <form:input path="password"/> 
        <form:errors path="password" cssClass="error"/> 
        <br><br> 
        confirm password: <form:input path="confPassword"/>
         <form:errors path="confPassword" cssClass="error"/>  
        <br><br> 
        <input type="submit" value="Submit"/>      
    </form:form>  
    

<form action="/login">
 
  <input type="submit" value="login">
</form>
</body>
</html>
