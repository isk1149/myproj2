<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="stocklist.model.StockInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>stockList</title>
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
<form action="stockList.do" method="post">
최근 <input type="text" name="period" size="1" style="text-align:right;" maxlength="2">년 동안 
[
<input type="checkbox" name="profit" value="sales" checked="checked">매출액
<input type="checkbox" name="profit" value="operatingprofit" checked="checked">영업이익
<input type="checkbox" name="profit" value="netprofit" checked="checked">순이익
<input type="checkbox" name="profit" value="eps" checked="checked">EPS
] 증가한 기업 
<input type="submit" value="검색">
</form>
<br>
<br>
<c:if test="${!empty errors.noYearOrProfitCheck}">년 수와 매출액, 영업이익, 순이익 중 최소 하나를 입력(선택)해주세요.</c:if>
<c:if test="${!empty errors.noResult}">
결과가 없습니다.
</c:if>
<c:if test="${empty errors.noResult}">
<%
	ArrayList<StockInfo> stockInfoList = (ArrayList<StockInfo>) request.getAttribute("stockInfoList");

	String str = (String) request.getAttribute("period");
	if (str != null) {
		int period = Integer.parseInt(str);
		period = period > 4 ? 4 : period;
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		Map<String, Boolean> map = new HashMap<>();
		int listIdx = 0;
		for (int i = 0; i < stockInfoList.size(); i++) {
			if (map.containsKey(stockInfoList.get(i).getStockCode())) continue;
%>
			<table>
				<tr>
					<th><%= stockInfoList.get(i).getCompanyName() %></th>
				<% for (int j = period; j > -1; j--) { %>
					<th> <%= nowYear - j - 1 %> </th>
				<% } %>
				</tr>
				<tr>
					<th>매출액</th>
				<% for (int j = 0; j <= period; j++) { %>
					<td align="right"> <%= stockInfoList.get(listIdx+j).getSales() %> </td>
				<% } %>
				</tr>
				<tr>
					<th>영업이익</th>
				<% for (int j = 0; j <= period; j++) { %>
					<td align="right"> <%= stockInfoList.get(listIdx+j).getOperatingProfit() %> </td>
				<% } %>
				</tr>
				<tr>
					<th>순이익</th>
				<% for (int j = 0; j <= period; j++) { %>
					<td align="right"> <%= stockInfoList.get(listIdx+j).getNetProfit() %> </td>
				<% } %>
				</tr>
				<tr>
					<th>eps</th>
				<% for (int j = 0; j <= period; j++) { %>
					<td align="right"> <%= stockInfoList.get(listIdx+j).getEps() %> </td>
				<% } %>
				</tr>
				<tr>
					<th>roe</th>
				<% for (int j = 0; j <= period; j++) { %>
					<td align="right"> <%= stockInfoList.get(listIdx+j).getRoe() %> </td>
				<% } %>
				</tr>
				
			</table>
			<br>
<% 		listIdx += period + 1;
		map.put(stockInfoList.get(i).getStockCode(), Boolean.TRUE);
		}
	}%>
</c:if>
</body>
</html>