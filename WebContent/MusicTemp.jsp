<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" 
	  xmlns:f="http://java.sun.com/jsf/core" 
	  xmlns:h="http://java.sun.com/jsf/html"
	  xmlns:c="http://java.sun.com/jsp/jstl/core"
	  xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	  xmlns:p="http://primefaces.org/ui">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Music Information</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/bootstrap-responsive.css"/>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/CSS/bootstrap.js"></script>
<body>
<f:view>
	<h:outputText value="#{userInformation.userName} likes" styleClass="text-info"></h:outputText>
	<br/>
	<br/>
	
	<!-- using jsp core tag library for iterating over the list
<table>
		<c:forEach items="#{userInformation.musicList}" var="musicList">
			<tr>
					<h:graphicImage value="#{musicList.picture}"></h:graphicImage>
					<h:outputText value=" #{musicList.name}"></h:outputText>
			</tr>
		</c:forEach>
</table> -->

<p:dataGrid var="musicList" value="#{userInformation.musicList}">
	<p:panel>
		<p:graphicImage value="#{musicList.picture}"></p:graphicImage>
		<h:outputText value=" #{musicList.name}"></h:outputText>
	</p:panel>
</p:dataGrid>
	<br/>
	<br/>
	<br/>
<table>
	<c:forEach items="#{friendInformation.friendName}" var="friendList" varStatus="status">
		<c:set var="count" value="${status.index}"></c:set>
		<c:out value="${userInformation.userName}'s friend ${friendInformation.friendName[count]} likes"></c:out>
		<br/>
			<c:forEach var="friendMusicList" items="#{friendInformation.friendMusicList[count]}">
				<c:out value="${friendMusicList.name}"></c:out>
				<img src="${friendMusicList.picture }"></img>
			</c:forEach>
		<br/>
		<c:out value="${userInformation.userName}'s friend ${friendInformation.friendName[count]} listens to"></c:out>
		<br/>
			<c:forEach var="friendDataMusicList" items="#{friendInformation.friendDataList[count]}">
				<c:out value="${friendDataMusicList.musicdata.song.title}"></c:out>
				<br/>
			</c:forEach>
		<br/>
	</c:forEach>
</table>
</f:view>
</body>
</html>