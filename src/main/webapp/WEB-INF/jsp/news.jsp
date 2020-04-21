    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8">
        <title>Newsline</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        </head>

        <body>
        <div>



        <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <th>Create a news</th>
        </thead>

        <tr>
            <td>
                <a href="/news/newsSaving" > Create </a>
            </td>
        </tr>
        </table>



        <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <th>ID</th>
        <th>Headline</th>
        <th>Post date</th>
        <th>News body</th>
        <th>Actions</th>
        </thead>
        <c:forEach items="${allNews}" var="news">
            <tr>
                <td>${news.id}</td>
                <td>${news.headline}</td>
                <td> <fmt:formatDate value="${news.postDate}" pattern="E, dd MMM yyyy HH:mm:ss" /> </td>
                <td> <a href="/news/newsFullVersion/${news.id}">View full news</a> </td>

                <td>

                <form action="${pageContext.request.contextPath}/news/newsSaving/${news.id}" method="get">
                <input type="hidden" name="newsId" value="${news.id}">
                <button type="submit">Update</button>
                </form>

                <p/>

                <form action="${pageContext.request.contextPath}/news/delete" method="delete">
                <input type="hidden" name="newsId" value="${news.id}">
                <button type="submit">Delete</button>
                </form>

                </td>


            </tr>
        </c:forEach>
        </table>



        <a href="/">Главная</a>
        </div>
        </body>
        </html>