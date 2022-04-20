<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>월별이자상세조회</title>
	<style>
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	th {width : 100px;}
	</style>
<jsp:include page="/headerStyle.jspf" flush="false"></jsp:include>
</head>
<jsp:include page="/header.jspf" flush="false"></jsp:include>
<body>

<c:if test="${!empty errors.selectQueryNoResult}">이자 내역을 불러올 수 없습니다.</c:if>
<c:if test="${empty errors.selectQueryNoResult}">
[계좌번호]<br>
<b>${accountInfo}</b><br><br>

<c:forEach var="list" items="${monthlyInterestDetailList}">
<div style="float:left; margin-right:100px;">${list.interestSupplyDate}</div>  
<div style="margin-right:10px; width:25%; text-align: right;">${list.interest}원 </div>
<br>
</c:forEach>

</c:if>
</body>
</html>