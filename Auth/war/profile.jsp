<%@page import="fr.onevu.auth.server.auth.Profile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="profile"> 
	<%=Profile.getProfileWidgetRules(request)%>
</div>