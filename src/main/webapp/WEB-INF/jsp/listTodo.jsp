<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
		<title>Todo List for ${name}</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<h1>Hey ${name}, Please have a look at your todo list</h1>
			<br/>
			
			<table class="table">
				<caption><h1>Your todos are</h1></caption>
				<thead>
					<tr>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is it done?</th>
						<%-- <th>Delete</th> --%>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todoList}" var="todo" >
						<tr>
							<td>${todo.getDesc()}</td>
							<td><fmt:formatDate pattern="MM/dd/yyyy"
									value="${todo.getTargetDate()}" /></td>
							<td>${todo.isDone()}</td>
							<td><a type="button" class="btn btn-success" href="/updateTodo?id=${todo.getId()}">Update</a></td>
							<td><a type="button" class="btn btn-warning" href="/deleteTodo?id=${todo.getId()}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>	
			
			<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
			<div>
				<a class="button" href="/addTodo">Click here</a> to add a todo
				<br/> <br/>
				<a href="/logout">Logout</a>
			</div>
			
		</div>
	</body>
</html>