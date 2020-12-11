<%-- 
    Document   : editSighting
    Created on : Dec 11, 2020, 12:32:07 AM
    Author     : TheFemiFactor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Sighting</title>
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/hero">Heros</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container">            
            <h2 class="text-center">Edit Sighting</h2>
            <form action="${pageContext.request.contextPath}/updateSighting" method="post">                        
                <div class="form-group">
                    <label for="sightingDate">Date:</label>
                    <input name="sightingDate" type="date" class="form-control" value="${sightingToEdit.date}"/>                            
                </div>    
                <div class="form-group">
                    <label for="heroID">Hero:</label>
                    <select class="form-control" name="heroID">
                        <c:forEach var="hero" items="${heros}">
                            <c:choose>
                                <c:when test="${hero.heroID == sightingToEdit.hero.heroID}">
                                    <option value="${sightingToEdit.hero.heroID}" selected>${sightingToEdit.hero.heroName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${hero.heroID}">${hero.heroName}</option>
                                </c:otherwise>
                            </c:choose>                            
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="locationID">Location:</label>
                    <select class="form-control" name="locationID">
                        <c:forEach var="location" items="${locations}">
                            <c:choose>
                                <c:when test="${location.locationID == sightingToEdit.location.locationID}">
                                    <option value="${sightingToEdit.location.locationID}" selected>${sightingToEdit.location.locationName}<c:if test="${sightingToEdit.location.streetAddress != null}">, ${sightingToEdit.location.streetAddress}</c:if></option>                                    
                                </c:when>
                                <c:otherwise>
                                    <option value="${location.locationID}">${location.locationName}<c:if test="${location.streetAddress != null}">, ${location.streetAddress}</c:if></option>
                                </c:otherwise>
                            </c:choose>                  
                        </c:forEach>
                    </select>
                </div>          
                <input name="sightingID" type="hidden" value="${sightingToEdit.sightingID}"/>
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>            
        </div>        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
