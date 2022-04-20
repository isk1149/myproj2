<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!--
			req.setAttribute("user", user);
			req.setAttribute("myAccountInfo", myAccountInfo);
			req.setAttribute("myBankCode", myBankbook);
			
			req.setAttribute("transactionMember", transactionMember);
			req.setAttribute("transactionAccountInfo", transactionAccountInfo);
			req.setAttribute("transactionBankCode", transactionBankCode); 
 -->
<form action="remittanceProcessing.do" method="post">
[보내는 사람]<br>
${user.name}
<input type="hidden" name="myBankCode" value="${myBankCode.bankCode}"><br>
${myBankCode.bankName}  ${myAccountInfo.accountNumber}<br><br>
<input type="hidden" name="myAccountNumber" value="${myAccountInfo.accountNumber}">
<input type="hidden" name="myAccountHolder" value="${user.id}">
 
[받는 사람]<br>
${transactionMember.name}<br>
<input type="hidden" name="transactionBankCode" value="${transactionBankCode.bankCode}">
${transactionBankCode.bankName}  ${transactionAccountInfo.accountNumber}<br><br>
<input type="hidden" name="transactionAccountNumber" value="${transactionAccountInfo.accountNumber}">
<input type="hidden" name="transactionAccountHolder" value="${transactionMember.id}">

[송금액]<br>
${transactionAmount}원<br>
<input type="hidden" name="transactionAmount" value="${transactionAmount}">

<input type="submit" value="이체">
</form>
<hr>
<!--
[보내는 사람]<br>
${user.name}<br>
${myBankCode.bankName}  ${myAccountInfo.accountNumber}<br><br>

[받는 사람]<br>
${transactionMember.name}<br>
${transactionBankCode.bankName}  ${transactionAccountInfo.accountNumber}<br><br>

[송금액]<br>
${transactionAmount}원
  -->
</body>
</html>