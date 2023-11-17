<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a User by UserName</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="userCreate"><a href="usercreate">Create User</a></div>
	<br/>
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Password</th>
            </tr>
            <c:if test="${not empty Users}">
	           <%--  <c:forEach items="${Users}" var="User" > --%>
	                <tr>
	                    <td><c:out value="${Users.getUserName()}" /></td>
	                    <td><c:out value="${Users.getFirstName()}" /></td>
	                    <td><c:out value="${Users.getLastName()}" /></td>
	                     <td><c:out value="${Users.getPassWord()}" /></td>
	                     <td><a href="userdelete?username=<c:out value="${Users.getUserName()}"/>">Delete</a></td>
	                    <td><a href="userupdate?username=<c:out value="${Users.getUserName()}"/>">Update</a></td>
	                </tr>
	            <%-- </c:forEach> --%>
            </c:if>
       </table>
       <p></p>
       <div id="findPokemons"><a href="findPokemons">Search for a Pokemon by PokemonID (1-3000)</a></div>
</body>
</html>
