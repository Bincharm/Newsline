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



        <!-- Pagination Bar -->
        <div c:fragment='paginationbar'>
            <div class='pagination pagination-centered'>
                <ul>
                    <li c:class='${newsPage.firstPage}? 'disabled' : '''>
                        <span c:if='${newsPage.firstPage}'>← First</span>
                        <a c:if='${not newsPage.firstPage}' c:href='@{${newsPage.url}(newsPage.page=1,newsPage.size=${newsPage.size})}'>← First</a>
                    </li>
                    <li c:class='${newsPage.hasPreviousPage}? '' : 'disabled''>
                        <span c:if='${not newsPage.hasPreviousPage}'>«</span>
                        <a c:if='${newsPage.hasPreviousPage}' c:href='@{${newsPage.url}(newsPage.page=${newsPage.number-1},newsPage.size=${newsPage.size})}' title='Go to previous page'>«</a>
                    </li>
                    <li c:each='item : ${newsPage.items}' c:class='${item.current}? 'active' : '''>
                        <span c:if='${item.current}' c:text='${item.number}'>1</span>
                        <a c:if='${not item.current}' c:href='@{${newsPage.url}(newsPage.page=${item.number},newsPage.size=${newsPage.size})}'><span c:text='${item.number}'>1</span></a>
                    </li>
                        <li c:class='${newsPage.hasNextPage}? '' : 'disabled''>
                        <span c:if='${not newsPage.hasNextPage}'>»</span>
                    <a c:if='${newsPage.hasNextPage}' c:href='@{${newsPage.url}(newsPage.page=${newsPage.number+1},newsPage.size=${newsPage.size})}' title='Go to next page'>»</a>
                    </li>
                    <li c:class='${newsPage.lastPage}? 'disabled' : '''>
                        <span c:if='${newsPage.lastPage}'>Last →</span>
                        <a c:if='${not newsPage.lastPage}' c:href='@{${newsPage.url}(newsPage.page=${newsPage.totalPages},newsPage.size=${newsPage.size})}'>Last →</a>
                    </li>
                </ul>
            </div>
        </div>


        <div id="pagination">

        <c:url value="/news" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/news" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/news" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">Next</a>
        </c:if>
        </div>



        <a href="/">Главная</a>
        </div>
        </body>
        </html>