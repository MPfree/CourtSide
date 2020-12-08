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
	<a class="page" href="/mie350webapp/MapController?action=get">   Find A Court   </a>

	<a class='page' href='/mie350webapp/PostController?action=get'>   Social Media   </a>
	<a class='page' href="about.jsp">About</a>
	<br>
	<br>
	</div>
<br>
<br>


	<p class='text'>check-in.</p>
	<form class="checkinform" method="POST" action="PostController">
		<label for="title">Title</label>
		<input type="text" id="title" name="title">
		<label for="description">Post Content</label>
		<input type="text" id="description" name="description">
		<input type="submit" class="button"></input>
	</form>


</body>
</html>