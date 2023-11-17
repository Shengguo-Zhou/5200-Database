<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Pokemon</title>
</head>
<body>
	<form action="findPokemons" method="post">
		<h1>Search for a Pokemon by PokemonID (1-3000)</h1>
		<p>
			<label for="id">ID</label>
			<input id="id" name="id" value="${fn:escapeXml(param.id)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching Pokemons</h1>
        <table border="1">
            <tr>
                <th>PokemonId</th>
                <th>Name</th>
                <th>Attack</th>
                <th>Defense</th>
                <th>HouseId</th>
            </tr>
            <c:if test="${not empty Pokemons}">
	            <%-- <c:forEach items="${Pokemons}" var="Pokemon" > --%>
	                <tr>
	                    <td><c:out value="${Pokemons.getPokemonId()}" /></td>
	                    <td><c:out value="${Pokemons.getName()}" /></td>
	                    <td><c:out value="${Pokemons.getAttack()}" /></td>
	                    <td><c:out value="${Pokemons.getDefense()}" /></td>
	                    <td><c:out value="${Pokemons.getHouseId()}" /></td>
	                </tr>
	            <%-- </c:forEach> --%>
            </c:if>
       </table>
</body>
</html>