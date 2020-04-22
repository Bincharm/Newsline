        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8">
        <title>Add a new word for the game</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        </head>

        <body>
        <div>


            <table class="table table-striped table-hover">
            <thead class="thead-dark">
            <th>Saving news</th>
            </thead>

            <tr>
                <td>
                    <form action="${pageContext.request.contextPath}/news" method="post"  enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${news.id}">
                    <input type="text" name="headline" value="${news.headline}">
                </td>
            </tr>
            <tr>
                <td>
                    <img alt="img" src="data:image/jpeg;base64,${news.base64imageFile}">
                    <p/>
                    <p> <input type="file" name="image"> </p>
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td>--%>
                    <%--<input type="text" name="postDate" value=${news.postDate}/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <td>
                <input type="text" name="newsBody" value="${news.newsBody}">
                <input type="hidden" name="action" value="save">
                </td>
            </tr>
            <tr>
                <td>
                <button type="submit">Save</button>
                </form>
                </td>
            </tr>
            </table>



        <a href="/">Главная</a>
        </div>
        </body>
        </html>