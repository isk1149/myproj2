<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>월별이자조회</title>
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

<div style="float:left; margin-right:100px;">${thisMonth}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.thisMonthInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${thisMonth}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${oneMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.oneMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${oneMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${twoMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.twoMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${twoMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${threeMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.threeMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${threeMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${fourMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.fourMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${fourMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${fiveMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.fiveMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${fiveMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${sixMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.sixMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${sixMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${sevenMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.sevenMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${sevenMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${eightMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.eightMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${eightMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${nineMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.nineMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${nineMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${tenMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.tenMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${tenMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

<div style="float:left; margin-right:100px;">${elevenMonthAgo}</div>
<div style="margin-right:10px; width:25%; text-align: right;">${monthlyInterest.elevenMonthAgoInterest}원
&nbsp&nbsp&nbsp
<div style="float:right;">
<form action="monthlyInterestDetail.do" method="post">
<input type="hidden" value="${elevenMonthAgo}" name="yearMonth">
<input type="submit" value="[상세보기]">
</form>
</div>
</div>
<br>

</c:if>
</body>
</html>