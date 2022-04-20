<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="transactionhistory.model.TransactionHistory" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="util.MoneyCommaFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계좌이체진행</title>
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
<c:if test="${!empty selectQueryNoResult}">계좌 거래내역을 불러올 수 없습니다.</c:if>
<c:if test="${empty selectQueryNoResult}">
[계좌번호]<br>
<b>${accountNumber}</b>
<br><br>

[거래내역]<br>
<%
ArrayList<TransactionHistory> transactionHistoryList = (ArrayList<TransactionHistory>) request.getAttribute("transactionHistoryList");
HashMap<Date, Boolean> map = new HashMap<>();
for (int i = 0; i < 6; i++) {
	Date date = transactionHistoryList.get(i).getTransactionDate();
	if (!map.containsKey(date)) {
		map.put(date, Boolean.TRUE);
%>
<div style=" margin-right:20px; width:200px;"><%= transactionHistoryList.get(i).getTransactionDate() %></div>
<%  } %>
<div style="float:left; margin-right:20px; width:50px;"><%= transactionHistoryList.get(i).getTransactionDateToChar().substring(11, 16) %></div>
<div style="float:left; margin-right:20px; width:100px;"><%= transactionHistoryList.get(i).getTransactionAccountHolderName() %></div>
<div style="float:left; margin-right:20px; width:150px; text-align: right;"><%= MoneyCommaFormat.format(transactionHistoryList.get(i).getTransactionAmount()) %>원</div>
<div style="float:left; margin-right:20px; width:50px;"><%= transactionHistoryList.get(i).getTransactionType() %></div>
<br>
<div style=" width:50px;"></div>
<%
}
%>
</c:if>
</body>
</html>