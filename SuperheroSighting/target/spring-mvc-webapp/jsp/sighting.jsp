<%-- 
    Document   : sighting
    Created on : Dec 11, 2020, 12:34:10 AM
    Author     : TheFemiFactor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">  
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Hero Sightings</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/hero">Heros</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <h2 class="text-center">Sightings</h2>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Hero</th>
                            <th>Location</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Zip Code</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th></th>
                        </tr>                            
                    </thead>
                    <tbody>
                        <c:forEach var="sighting" items="${sightings}">
                            <tr>
                                <td>${sighting.date}</td>
                                <td>${sighting.hero.heroName}</td>
                                <td>${sighting.location.locationName}</td>
                                <td>${sighting.location.city}</td>
                                <td>${sighting.location.state}</td>
                                <td>${sighting.location.zipcode}</td>
                                <td>${sighting.location.latitude}</td>
                                <td>${sighting.location.longitude}</td>
                                <td>
                                    <a href="deleteSighting/${sighting.sightingID}">Delete</a> | 
                                    <a href="editSighting/${sighting.sightingID}">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>        
            
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>