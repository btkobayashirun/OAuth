<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<body>
    <div id="wrapper">
        <h1>OAuth Approval</h1>
        <p>Do you authorize '${f:h(authorizationRequest.clientId)}' to access your protected resources?</p>
        <form id='confirmationForm' name='confirmationForm' action='${pageContext.request.contextPath}/oauth/authorize' method='post'>
            <c:forEach var="scope" items="${scopes}" >
                <li>
                    ${f:h(scope.key)}
                    <input type='radio' name="${f:h(scope.key)}" value='true'/>Approve
                    <input type='radio' name="${f:h(scope.key)}" value='false'/>Deny
                </li>
            </c:forEach>
            <input name='user_oauth_approval' value='true' type='hidden'/>
            <sec:csrfInput />
            <label>
                <input name='authorize' value='Authorize' type='submit'/>
            </label>
        </form>
    </div>
</body>
