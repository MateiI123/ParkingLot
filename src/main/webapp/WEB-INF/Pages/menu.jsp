<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header data-bs-theme="dark">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid"> <a class="navbar-brand" href="${pageContext.request.contextPath}">ParkingLot</a>
            @@ -11,20 +12,33 @@
            eq '/about.jsp' ? ' active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/about.jsp">About</a>
            </li>
            <li class="nav-item">
                <c:if test = "${pageContext.request.isUserInRole('READ_CARS')}">
                    <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/"))
                eq '/cars.jsp' ? ' active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/Cars">Cars</a>
                </c:if>
            </li>
            <li class="nav-item">
                <c:if test = "${pageContext.request.isUserInRole('READ_USERS')}">
                    <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf("/"))
                    eq '/users.jsp' ? ' active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/Users">Users
                    </a>
                </c:if>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" aria-disabled="true">Disabled</a>
            </li>
            </ul>
            <ul class="navbar-nav">
                <li class = "nav-item">

                    <c:choose>
                        <c:when test="${pageContext.request.getRemoteUser() == null}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>