<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<title style='font-family: Arial; font-size: 30px;'>Courtside: Check-In</title>
	<link rel='icon' href='https://www.freepnglogos.com/uploads/basketball-png/basketball-open-letter-3.png'>	
	<link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>

<body>
	<div class="banner">
		<div id ="content">
				<img src= "courtside_logo.png"
					class = "logo"
					width="300" height="60"
					alt = "Courtside Logo"/>
		</div>
		<a class="page" href="/mie350webapp/MapController?action=get">   Find A Court   </a>
	
		<a class='page' href='/mie350webapp/PostController?action=get'>   Social Media   </a>
		<a class='page' href="about.jsp">About</a>
	<br>
	<br>
	</div>
<br>
<br>


	<p class='text'>Give the court a rating</p>
	<form class="checkinform" method="POST" action="RatingController">
		<label for="userRating">Rating</label>
		<input type="number" id="userRating" name="userRating">
		<input type="submit" class="button"></input>
	</form>


</body>
</html>