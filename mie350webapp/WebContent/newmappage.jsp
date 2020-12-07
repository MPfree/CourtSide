<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <title>Add Map</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAk-L2RmcuHyAFPCqgP6rxBLtDZiULpHsU&callback=initMap&libraries=&v=weekly"
      defer
    ></script>
    <link rel="stylesheet" type="text/css" href="css/mystyle.css">
    <style type="text/css">
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 70%;
      }

      /* Optional: Makes the sample page fill the window. */
      html,
      body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
    <script>
     // Initialize and add the map
      function initMap() {
        // The location of Uluru
        const toronto = { lat: 43.6532, lng: -79.3832 };
        const map = new google.maps.Map(document.getElementById("map"), {
            zoom: 7,
            center: toronto,
          });
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
        map.addListener("click", (mapsMouseEvent) => {
            const latlong = JSON.stringify(mapsMouseEvent.latLng.toJSON(), null, 2)
            const latlong_obj = JSON.parse(latlong)
            getAddress(latlong_obj.lat, latlong_obj.lng)
            placeMarker(latlong_obj.lat, latlong_obj.lng, map)
          });
      }
	</script>
    <script>
      async function getAddress(lat, lng){
    	const request = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=AIzaSyAk-L2RmcuHyAFPCqgP6rxBLtDZiULpHsU"
        const response = await fetch(request)
        const json_obj = await response.json()
        const address = json_obj.results[0].formatted_address
        document.getElementById("address").value = address
      }
      function placeMarker(lat, lng, map){
        const marker = new google.maps.Marker({
          position: {lat:lat, lng:lng},
          map: map,
        });
        const infowindow = new google.maps.InfoWindow({
            content: "lat: " + lat + ", lng: " + lng,
          });
        infowindow.open(map, marker)
      }
    </script> 
  </head>
  <body>
    <h3>My Google Maps Demo</h3>
    <!--The div element for the map -->
    <div id="map"></div>
    <p class='title'>Add A New Court</p>
	

    <form class='newcourtform' action="MapController" method="POST">
        <label for="courtName">Court Name:</label><br>
        <input type="text" id="courtName" name="courtName"><br><br>
        <label for="numNets">Number of Nets:</label><br>
        <input type="text" id="numNets" name="numNets"><br><br>
        <label for="location">Location:</label><br>
        <input id="address" type="text" name="location"><br><br><br>
        <label for="doubleRim">Double Rim:</label><br>
        <input id="doubleRim" type="text" name="doubleRim"><br><br><br>
        <label for="rating">Rating:</label><br>
        <input id="rating" type="number" name="rating"><br><br><br>
        <input type="submit" value="Submit" class='button'><br>

        
      </form>
      <h2 id="location"></h2>
  </body>
</html>
<!-- aight imma go off -->