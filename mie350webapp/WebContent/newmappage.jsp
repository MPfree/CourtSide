<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <title>Add Map</title>
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAIUlJfUiTuAmLyj4IEQtJVTr9uOSku8zQ&callback=initMap&libraries=&v=weekly"
      defer
    ></script>
    <style type="text/css">
      /* Set the size of the div element that contains the map */
      #map {
        height: 500px;
        /* The height is 400 pixels */
        width: 100%;
        /* The width is the width of the web page */
      }
    </style>
    <script>
      // Initialize and add the map
      const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 7,
          center: toronto,
        });
      function initMap() {
        // The location of Uluru
        const toronto = { lat: 43.6532, lng: -79.3832 };
        // The map, centered at Uluru
        // The marker, positioned at Uluru
        const marker = new google.maps.Marker({
          position: toronto,
          map: map,
        });

          // Create the initial InfoWindow.
        let infoWindow = new google.maps.InfoWindow({
          content: "Click the map to get Lat/Lng!",
          position: toronto,
        });
        infoWindow.open(map);

        // Configure the click listener.
        map.addListener("click", (mapsMouseEvent) => {
          const latlong = JSON.stringify(mapsMouseEvent.latLng.toJSON(), null, 2)
          const latlong_obj = JSON.parse(latlong)
          const address = getAddress(latlong_obj.lat, latlong_obj.lng)
          document.getElementById("address").innerHTML = address
          placeMarker(latlong_obj.lat, latlong_obj.lng)
        });
      }
    </script>
    <script>
      async function getAddress(lat, long){
        const response = await fetch(`https://maps.googleapis.com/maps/api/geocode/json?latlng=${lat},${long}&key=AIzaSyAIUlJfUiTuAmLyj4IEQtJVTr9uOSku8zQ`)
        const json_obj = await response.json()
        const address = json_obj.results[0].formatted_address
        return address
      }
      function placeMarker(lat, long){
        const marker = new google.maps.Marker({
          position: {lat:lat, lng:long},
          map: map,
        });
      }
    </script>
  </head>
  <body>
    <h3>My Google Maps Demo</h3>
    <!--The div element for the map -->
    <div id="map"></div>
    <p class='title'>Add A New Court</p>
	

    <form class='newcourtform'>
        <label for="courtName">Court Name:</label><br>
        <input type="text" id="courtName" name="courtName"><br><br>
        <label for="numNets">Number of Nets:</label><br>
        <input type="text" id="numNets" name="numNets"><br><br>
        <label for="location">Location:</label><br>
        <input id="address" type="text" id="location" name="location"><br><br><br>
        <input type="submit" value="Submit" class='button'><br>

        
      </form>
  </body>
</html>
<!-- aight imma go off -->