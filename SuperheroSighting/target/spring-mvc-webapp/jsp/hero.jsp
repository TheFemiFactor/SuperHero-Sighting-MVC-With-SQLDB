<%-- 
    Document   : hero
    Created on : Dec 11, 2020, 12:32:32 AM
    Author     : TheFemiFactor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Heros</title>
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
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/hero">Heros</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                    </ul>
                    <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4">
                    <button id="showHerosButton" type="button" class="btn btn-lg btn-default">Heros</button>
                    <button id="addHeroButton" type="button" class="btn btn-lg btn-default">Add Hero</button>
                </div>
                <div class="col-sm-4">
                    <h2 class="toggleHeader"></h2>
                </div>
            </div>
            <div id="showHerosDiv">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th></th>
                            </tr>                            
                        </thead>
                        <tbody>
                            <c:forEach var="hero" items="${heros}">
                                <tr>
                                    <td>${hero.heroName}</td>
                                    <td>${hero.description}</td>
                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/deleteHero/${hero.heroID}">Delete</a> | 
                                        <a href="${pageContext.request.contextPath}/editHero/${hero.heroID}">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="addHeroDiv">
                <div class="row">
                    <div class="col-sm-12 col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
                        <form action="${pageContext.request.contextPath}/addHero" method="post">
                            <div class="form-group">
                                <label for="heroName">Hero Name:</label>
                                <input name="heroName" type="text" class="form-control"/>                            
                            </div>                        
                            <div class="form-group">
                                <label for="heroDescription">Hero Description:</label>
                                <textarea name="heroDescription" class="form-control" rows="10"></textarea>
                            </div>                        
                            <button type="submit" class="btn btn-primary">Submit</button> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/hero.js"></script>
    </body>
</html>
