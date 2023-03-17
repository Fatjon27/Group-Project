<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
    <style>
        body{
            background-image: url(../images/foto-projekti2.webp);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
        }
    </style>

</head>
<body class="bg-light">
<div class="container">
    <div class="d-flex flex-column justify-content-center align-items-center gap-2">
        <h1 class="text-white">Login/Register</h1>
    </div>
    <div class="d-flex justify-content-center gap-5 mt-3">
        <div class = "bg-light p-5">
            <h3 class="align-items-center justify-content-center d-flex">Register</h3>
            <%--@elvariable id="newUser" type="java"--%>
            <form:form class="form d-flex flex-column gap-3" action="/register" method="post" modelAttribute="newUser">
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <form:errors path="name" class="errors text-danger"></form:errors>
                    <form:label path="name">Name: </form:label>
                    <form:input class="form-control" type="text" path="name"></form:input>
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <form:errors path="email" class="errors text-danger"></form:errors>
                    <form:label path="email">Email: </form:label>
                    <form:input class="form-control" type="text" path="email"></form:input>
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <form:errors path="password" class="errors text-danger"></form:errors>
                    <form:label path="password">Password: </form:label>
                    <form:input class="form-control" type="password" path="password"></form:input>
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <form:errors path="confirm" class="errors text-danger"></form:errors>
                    <form:label path="confirm">Confirm Password: </form:label>
                    <form:input class="form-control" type="password" path="confirm"></form:input>
                </div>
                <input class="btn btn-dark" type="submit" value="Submit"/>
            </form:form>
        </div>
        <div class = "bg-light p-5">
            <h3 class="align-items-center justify-content-center d-flex">Log In</h3>
            <%--@elvariable id="newLogin" type="java"--%>
            <form:form class="form d-flex flex-column gap-3" action="/login" method="post" modelAttribute="newLogin">
                <div class="d-flex flex-column justify-content-center align-items-center ">
                    <form:errors path="email" class="errors text-danger"></form:errors>
                    <form:label path="email">Email: </form:label>
                    <form:input class="form-control" type="text" path="email"></form:input>
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center">
                    <form:label path="password">Password: </form:label>
                    <form:input class="form-control" type="password" path="password"></form:input>
                    <form:errors path="password" class="errors text-danger"></form:errors>
                </div>
                <input class="btn btn-dark" type="submit" value="Submit"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>