<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="fistname" placeholder="Fistname"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="lastname" placeholder="Lastname"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="text" path="userName" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="userName"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="text" path="email" placeholder="email"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="password" path="passWord" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>