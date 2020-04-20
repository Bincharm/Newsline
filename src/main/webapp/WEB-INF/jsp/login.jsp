    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="utf-8">
        <title>Log in with your account</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        </head>

        <body>
        <sec:authorize access="isAuthenticated()">
                <% response.sendRedirect("/"); %>
        </sec:authorize>
        <div>
        <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
        <input  class="form-control" name="username" type="text" placeholder="Username"
        autofocus="true"/>
        <input class="form-control" name="password" type="password" placeholder="Password"/>
        <button class="btn btn-primary" type="submit">Log In</button>
        <h4><a href="/registration">Зарегистрироваться</a></h4>
        </div>
        </form>
        </div>

        </body>
        </html>
