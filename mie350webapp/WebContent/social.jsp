<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<title style='font-family: Arial; font-size: 30px;'>Courtside: Social Media</title>
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
	
	<p class='title'>${courtName}</p>
	<p class='subtext'>${address } </p>
	<p class='subtext'>Number of nets ${numberNets } </p>
	<p class='subtext'>Double rim: ${doubleRim } </p>
	<p class='subtext'>Rating: ${rating } </p>
	<div class="wrapper">
    <a type="button" class="button" href='newpost.jsp'>New Post</a>
    <a type="button" class="button" href='updateRating.jsp'>
    Rate this court</a>
    <br><br>
    </div>
	<div class='wrapper'>
		<c:forEach items="${posts}" var="post">
			<div class='post'>
			<p class='title2'>Title: ${post.value.getTitle() }</p>
			Post: ${post.value.getDescription() }
			<a class="button" type="button" href='/mie350webapp/PropsController?
			postID=${post.value.getPostID() }'>
			Props</a>
			<c:forEach items="${post.value.getComments()}" var="comment">
				<p>${comment.getComment() } </p>
			</c:forEach>
			</div>				
		</c:forEach>
	
	</div>
	<img src= "kawhi.png"
			class = "center"
			width="200" height="250"
			alt = "Kawhi"/>



	

</body>
</html>