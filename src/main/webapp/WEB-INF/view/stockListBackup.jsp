<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	최근 <input type="text" name="year" size="1" style="text-align:right;">년 동안 
	[
	<input type="checkbox" name="profit" value="sales" checked="checked">매출액
	<input type="checkbox" name="profit" value="operatingprofit" checked="checked">영업이익
	<input type="checkbox" name="profit" value="netprofit" checked="checked">순이익
	<input type="checkbox" name="profit" value="eps" checked="checked">EPS
	] 증가한 기업 
	<input type="submit" value="검색">
	<c:if test="${!empty errors.noYearOrProfitCheck}">년 수와 매출액, 영업이익, 순이익 중 최소 하나를 입력(선택)해주세요.</c:if>
	</form>
	<br>
	<br>

		<table>
		<tr>
			<th>삼성전자</th>
		    <th>2019</th>
		    <th>2020</th>
		    <th>2021</th>
		    <th>연평균증가율</th>
		</tr>
		<tr>
		    <td align="center">매출액</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">영업이익</td>
		    <td align="right">123123</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">순이익</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">EPS</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">ROE</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="center">-</td>
		</tr>
	</table>


	<table>
		<tr>
			<th>SK하이닉스</th>
		    <th>2019</th>
		    <th>2020</th>
		    <th>2021</th>
		    <th>연평균증가율</th>
		</tr>
		<tr>
		    <td align="center">매출액</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">영업이익</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">순이익</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">EPS</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		</tr>
		<tr>
		    <td align="center">ROE</td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="right"></td>
		    <td align="center">-</td>
		</tr>
	</table>
</body>
</html>