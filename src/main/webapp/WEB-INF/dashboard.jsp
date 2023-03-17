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
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->


</head>
<body class="bg-light">
<nav class="navbar navbar-expand-xl navbar-dark bg-dark container">
    <div class="container-fluid">
        <div class="collapse navbar-collapse show" id="navbarDark">
            <ul class="navbar-nav me-auto mb-2 mb-xl-0">
                <li class="nav-item">
                    <h2 class="nav-link active text-center align-items-center" aria-current="page">Hello ${user.name}!</h2>
                </li>
            </ul>
            <form class="d-flex" action="/logout" method="get">
                <button class="btn btn-outline-light" type="submit">Logout</button>
            </form>
        </div>
    </div>
</nav>
<div class="d-flex" style="margin: auto;width: 1400px">
    <div style="margin: auto;border-bottom: 2px solid gray">
        <h1>Welcome to Quotes!</h1>
        <h3>Please feel free to share your favorite quotes with the world !</h3>
    </div>
    <div style="margin: auto">
        <%--@elvariable id="quote" type="java"--%>
        <form:form action="/new/quotes" method="post" modelAttribute="quote">
            <div><form:errors path="author" class="text-danger"/></div>
            <div><form:errors path="description" class="text-danger"/></div>
            <div style="margin-top: 10px">
                <form:label path="author"><h6>Author:</h6></form:label>
                <form:input path="author" cssStyle="margin-left: 85px"/>
            </div>

            <div style="margin-top: 10px">
                <form:label path="description"><h6>Description:</h6> </form:label>
                <form:input path="description" cssStyle="margin-left: 65px"/>
            </div>


            <div style="margin-top: 50px;margin-left: 200px">
                <button style="box-shadow: 3px 3px black; border: 2px solid black; font-weight: 600; color: black; padding: 0px 5px; margin-bottom: 10px" class="btn">Submit</button>
            </div>
        </form:form>
    </div>
</div>
<div class="container" style="margin: auto;border: 2px solid lightgray;width: auto">
    <table class="table table-striped" style="margin: auto; margin-top: 10px ; width: 1300px;height: auto ;border: 2px solid black" >
        <thead style="background: #cccccc">
        <tr>
            <th>Author</th>
            <th>Description</th>
            <th>Leave a Comment</th>
            <th>Comments</th>
            <th>Like</th>
            <th>Likes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="quote" items="${quotes}">
            <tr>
                <td>
                    <c:out value="${quote.author}"/>
                    <c:if test="${quote.user.id==loggedInUserId}">
                        <a href="/quote/${quote.id}/delete">Delete</a>
                    </c:if>
                </td>
                <td>
                    <c:out value="${quote.description}"/>
                </td>
                <td>
                        <%--@elvariable id="comment" type="java"--%>
                    <form:form action="/new/comment/${quote.id}" method="post" modelAttribute="comment">
                        <div><form:errors path="text" class="text-danger"/></div>
                        <div>
                            <form:label path="text">Comment: </form:label>
                            <form:input path="text" cssStyle="margin-left: 85px"/>
                        </div>
                        <div style="margin-top: 10px;margin-left: 200px">
                            <button style="box-shadow: 3px 3px black; border: 2px solid black; font-weight: 600; color: black; padding: 0px 5px; margin-left: 10px" class="btn">Submit</button>
                        </div>
                    </form:form>
                </td>

                <td>
                    <c:forEach var="comment" items="${quote.comments}">
                        <h5><c:out value="${comment.user.name}"/>:</h5>
                        <c:if test="${comment.user.id==loggedInUserId}">
                            <a href="/comment/${comment.id}/delete">Delete</a>
                        </c:if>
                        <c:out value="${comment.text}"/>

                    </c:forEach>
                </td>
                <td>
                    <c:if test="${(!user.getLikes().contains(quote))}">
                        <%--@elvariable id="quote" type="java"--%>
                        <form action="/new/like/${quote.id}" method="post">
                            <div style="margin: auto">
                                <button style="box-shadow: 3px 3px black; border: 2px solid black; font-weight: 600; color: black; padding: 0px 5px; margin: auto" class="btn">Like</button>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${(user.getLikes().contains(quote))}">
                        <%--@elvariable id="quote" type="java"--%>
                        <form action="/remove/like/${quote.id}" method="post">
                            <div style="margin: auto">
                                <button style="box-shadow: 3px 3px black; border: 2px solid black; font-weight: 600; color: black; padding: 0px 5px; margin: auto" class="btn">Unlike</button>
                            </div>
                        </form>
                    </c:if>
                </td>
                <td>
                    <h4>${quote.likes.size()}</h4>
                    <c:forEach var="like" items="${quote.likes}">
                        <c:out value="${like.name}"/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>