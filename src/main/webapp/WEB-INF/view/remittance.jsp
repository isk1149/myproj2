<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계좌이체화면</title>
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
{ 홍길동 계좌 : 0000003402 }<br> 
&nbsp 김철수 계좌 : 0000006747 }
<br><br>
[계좌이체]<br>
<form action="remittance.do" method="post">
은행 : <input type="radio" name="bankcode" value="000001" checked="checked">대한은행 &nbsp&nbsp
	  <input type="radio" name="bankcode" value="000002" disabled>현대은행 &nbsp&nbsp
	  <input type="radio" name="bankcode" value="000003" disabled>미래은행
<br>
계좌번호 : <input type="text" name="accountNumber" value="${param.accountNumber}" size="10" maxlength="10"><br>
<c:if test="${errors.noBankCodeOrAccountNumber}">계좌가 없습니다.<br></c:if>
<c:if test="${errors.myAccountNumber}">본인 계좌입니다.<br></c:if>
이체금액 : <input type="number" name="transactionAmount"><br>
<c:if test="${errors.notNumber}">숫자가 아닙니다.<br></c:if>
<c:if test="${errors.insufficientDeposit}">잔액이 부족합니다.<br></c:if>
<input type="submit" value="확인">
</form>
<br>
<c:if test="${errors.selectQueryNoResult}">계좌가 조회되지 않습니다.<br></c:if>
<c:if test="${errors.Exception}">에러가 발생하였습니다.<br></c:if>

</body>
</html>