<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Spring Boot First Web App</title>
	</head>
	<body>
		
		<p>Please insert the description of your todo</p>
		<br/>
		<form:form method="post" modelAttribute="todo">
			<%-- <form:hidden path="id"/> --%>
			<form:label path="desc">Description</form:label> 
			<form:input path="desc" type="text" required="required"/> <br/>
			<form:errors path="desc"></form:errors>
			
			<form:label path="targetDate">Target Date</form:label> 
			<form:input path="targetDate" type="text" required="required"/> <br/>
			<form:errors path="targetDate"></form:errors>
			
			<button type="submit">Add Todo</button>
		</form:form>
		
	</body>
</html>