<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<title style='font-family: Arial; font-size: 30px;'>Courtside: Maps</title>
	<link rel='icon' href='https://www.freepnglogos.com/uploads/basketball-png/basketball-open-letter-3.png'>	
	<link rel="stylesheet" type="text/css" href="css/mystyle.css">
	
</head>

<body>
<div class="banner">
		<div id ="center">
				<img src= "courtside_logo.png"
					class = "logo"
					width="300" height="60"
					alt = "Courtside Logo"/>
		</div>
		<a class='page' href="about.jsp">About</a>
	<br>
	<br>
	</div>
	<p class='text'>find a court.</p>
	<div class='wrapper'>
	<a type="button" class="button" href='newmappage.jsp'>Add A New Court</a>
	</div>
	<br><br>
	<c:forEach items="${courts}" var="court">
		<a id="${court.getCourtID()}" type="button" class="button" 
		style="align-self: center" 
		href='/mie350webapp/MapController?action=select&
		courtID=${court.getCourtID()}&courtName=${court.getCourtName()}'>
		${court.getCourtName()}
		</a>						
	</c:forEach>

	
		


</body>
</html>