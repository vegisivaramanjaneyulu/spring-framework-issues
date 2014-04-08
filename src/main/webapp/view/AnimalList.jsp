<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
<em>Welcome! <c:out value="${greeting}"></c:out> <c:out value="${special}"></c:out></em>

<hr />
	<h1>Example for Spring MVC Excel Export</h1>

	<h2>Animal List</h2>

	<table border="1px" cellpadding="8px">
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Type</td>
			<td>Aggressive</td>
			<td>Weight</td>
		</tr>

		<c:forEach items="${animalList}" var="animal">
			<tr>
				<td><c:out value="${animal.id}" /></td>
				<td><c:out value="${animal.animalName}" /></td>
				<td><c:out value="${animal.animalType}" /></td>
				<td><c:out value="${animal.aggressive}" /></td>
				<td><c:out value="${animal.weight}" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
 --%>