<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:p="http://primefaces.org/ui">
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