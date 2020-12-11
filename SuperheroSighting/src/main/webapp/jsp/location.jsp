<%-- 
    Document   : location
    Created on : Dec 11, 2020, 12:33:01 AM
    Author     : TheFemiFactor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"> 
        <link href="${pageContext.request.contextPath}/css/superhero.css" rel="stylesheet"> 
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/hero">Heros</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4">
                    <button id="showLocationTableButton" class="btn btn-lg btn-default">Locations</button>
                    <button id="showAddLocationFormButton" class="btn btn-lg btn-default">Add Location</button>
                </div>
                <div class="col-sm-4">
                    <h2 class="toggleHeader"></h2>
                </div>
            </div>
                <div id="locationTableDiv">                    
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Street</th>
                                    <th>City</th>
                                    <th>State</th>
                                    <th>ZIP Code</th>
                                    <th>Lat.</th>
                                    <th>Long.</th>
                                    <th></th>
                                </tr>                            
                            </thead>
                            <tbody>
                                <c:forEach var="location" items="${locations}">
                                    <tr>
                                        <td>${location.locationName}</td>
                                        <td>${location.description}</td>
                                        <td>${location.streetAddress}</td>
                                        <td>${location.city}</td>
                                        <td>${location.state}</td>
                                        <td>${location.zipcode}</td>
                                        <td>${location.latitude}</td>
                                        <td>${location.longitude}</td>
                                        <td>
                                            <a href="deleteLocation/${location.locationID}">Delete</a> | 
                                            <a href="editLocation/${location.locationID}">Edit</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="addLocationDiv">                    
                    <form action="${pageContext.request.contextPath}/addLocation" method="post">
                        <div class="form-group">
                            <label for="locationName">Location Name:</label>
                            <input name="locationName" type="text" maxlength="45" class="form-control"/>                            
                        </div>                        
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea name="description" maxlength="300" class="form-control" rows="10"></textarea>
                        </div>                                                
                        <div class="form-group">
                            <label for="streetAddress">Street Address:</label>
                            <input name="streetAddress" type="text" maxlength="45" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="city">City:</label>
                            <input name="city" type="text" maxlength="45" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="state">State (must use abbreviation):</label>
                            <input name="state" type="text" maxlength="2" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="zipcode">ZIP Code:</label>
                            <input name="zipcode" type="text" maxlength="10" class="form-control"/>                            
                        </div>         
                        <div class="form-group">
                            <label for="latitude">Latitude:</label>
                            <input name="latitude" type="text" class="form-control"/>                            
                        </div>     
                        <div class="form-group">
                            <label for="longitude">Longitude:</label>
                            <input name="longitude" type="text" class="form-control"/>                            
                        </div>     
                        <button type="submit" class="btn btn-primary">Submit</button> 
                    </form>
                </div>
        </div>
        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/location.js"></script>
    </body>
</html>
