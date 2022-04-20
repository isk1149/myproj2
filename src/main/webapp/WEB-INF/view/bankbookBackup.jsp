<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bankbook.model.TransactionHistory" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="util.MoneyCommaFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파킹통장</title>
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
${authUser.name}님, 예금 정보입니다. <c:if test="${!empty authUser}"><a href="logout.do">[로그아웃]</a></c:if>
<br><br>
<!-- 
<c:if test="${errors.abc}">111</c:if> 없는 key를 가져오면 test에서 false 됨
<c:if test="${!errors.abc}">222</c:if> 없는 키라도 가져와서 not 연산자 붙이면 true가 된다.
 -->
 <!-- 없는 속성을 사용하려고 하면 당연히 안나옴
<c:if test="${aaa.abc}">111</c:if>
<c:if test="${!aaa.abc}">222</c:if>
 -->
<c:if test="${!empty errors.selectQueryNoResult}"><b>계좌 정보를 불러오는 중 오류가 발생하였습니다.</b></c:if>
<c:if test="${empty errors.selectQueryNoResult}">
[계좌번호]<br>
<b>${accountNumber}</b>
<br><br>

[예금액]<br>
<b style="font-size : 30px">${depositFormat}원</b><br>

<div style="float:left; margin-right:10px;">
<form action="remittance.do" method="get"><input type="submit" value="계좌이체"></form>
</div>
<div style="float:left; margin-right:10px;">
<form action="allTransactionHistory.do" method="get"><input type="submit" value="거래내역 전체조회"></form>
</div>
<div style="float:left; margin-right:10px;">
<form action="monthlyInterest.do" method="get"><input type="submit" value="이자월별조회"></form>
</div>
<br><br>

[지급가능이자]<br>
<form action="bankbook.do" method="post">
<b>${interestApplyDayCount}</b>일 동안 <b>${interestFormat}원</b>이 쌓였습니다.
<input type="submit" value="지금받기">
</form>
<br>

[거래내역]<br>
<%
ArrayList<TransactionHistory> transactionHistoryList = (ArrayList<TransactionHistory>) request.getAttribute("transactionHistoryList");
HashMap<Date, Boolean> map = new HashMap<>();
for (int i = 0; i < 5; i++) {
	Date date = transactionHistoryList.get(i).getTransactionDate();
	if (!map.containsKey(date)) {
		map.put(date, Boolean.TRUE);
%>
<%= transactionHistoryList.get(i).getTransactionDate() %><br>
<%  } %>

<%= transactionHistoryList.get(i).getTransactionDateToChar().substring(11, 16) %>&nbsp&nbsp&nbsp
<%= transactionHistoryList.get(i).getTransactionAccountHolderName() %>&nbsp&nbsp&nbsp
<%= MoneyCommaFormat.format(transactionHistoryList.get(i).getTransactionAmount()) %>원&nbsp&nbsp&nbsp
<%= transactionHistoryList.get(i).getTransactionType() %><br>
<%
}
%>




<!--
<div style="border: 1px solid #48BAE4; height: auto; width: 40%; float:left;">
</div>
-->
</c:if>
</body>
</html>