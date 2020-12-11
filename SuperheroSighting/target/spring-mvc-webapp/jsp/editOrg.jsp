<%-- 
    Document   : editOrg
    Created on : Dec 11, 2020, 12:31:38 AM
    Author     : TheFemiFactor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Organization</title>
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
            <h2 class="text-center">Edit Organization</h2>
            <form action="${pageContext.request.contextPath}/updateOrg" method="post">
                <div class="form-group">
                    <label for="orgName">Organization Name:</label>
                    <input name="orgName" type="text" maxlength="45" class="form-control" value="${orgToEdit.orgName}"/>                            
                </div>                        
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea name="description" maxlength="300" class="form-control" rows="10">${orgToEdit.description}</textarea>
                </div>                                                
                <div class="form-group">
                    <label for="streetAddress">Street Address:</label>
                    <input name="streetAddress" type="text" maxlength="45" class="form-control" value="${orgToEdit.streetAddress}"/>                            
                </div>  
                <div class="form-group">
                    <label for="city">City:</label>
                    <input name="city" type="text" maxlength="45" class="form-control" value="${orgToEdit.city}"/>                            
                </div>  
                <div class="form-group">
                    <label for="state">State (must use acronym):</label>
                    <input name="state" type="text" maxlength="2" class="form-control" value="${orgToEdit.state}"/>                            
                </div>  
                <div class="form-group">
                    <label for="zipcode">ZIP Code:</label>
                    <input name="zipcode" type="text" maxlength="10" class="form-control" value="${orgToEdit.zipcode}"/>                            
                </div>            
                <input type="hidden" name="orgID" value="${orgToEdit.orgID}"/>
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
