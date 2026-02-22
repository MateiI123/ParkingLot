<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>

    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <a href="${pageContext.request.contextPath}/AddUser"
           class="btn btn-primary btn-lg">
            Add user
        </a>
        <hr/>
    </c:if>

    <div class="container text-center">
    <c:forEach var="user" items="${users}">
        <div class="row">
            <div class="col">${user.username}</div>
            <div class="col">${user.email}</div>
        </div>
    </c:forEach>
    </div>