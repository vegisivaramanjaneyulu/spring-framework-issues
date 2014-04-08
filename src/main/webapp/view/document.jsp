<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
    <head>
        <title>Result</title>
    </head>
    <body>
<em>Welcome! <c:out value="${greeting}"></c:out> <c:out value="${special}"></c:out></em>
<hr />
        <h1><c:out value="${word}"></c:out></h1>
        
        
        
        
       
    </body>
</html>

