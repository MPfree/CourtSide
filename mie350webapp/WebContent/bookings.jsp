<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<title style='font-family: Arial; font-size: 30px;'>Courtside: Court Page</title>
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

	<br>
	<br>
	</div>
<br>
<br>

    <p class='title'>${courtName}</p>
    <div class="wrapper">
    <a type="button" class="button" href='checkin.jsp'>New Booking</a>
    <br><br>
    </div>
    

    <div class='wrapper'>
    
    <c:forEach items="${bookings}" var="booking_list">
    <div class="day">
	    <div class="daytitle">
				${booking_list.key}
		</div><br><br>
		<c:forEach items="${booking_list.value}" var="booking">
			<div class='booking'>
				TeamSize: ${booking.getteamSize() }
				Date: ${booking.getsignupDate()} 
				Time: ${booking.getsignupTime() }
	        </div><br><br>
	    </c:forEach>
	</div>					
	
        
    </c:forEach>

   </div>





</body>
</html>