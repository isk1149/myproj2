<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.connection.ConnectionProvider" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB연결 테스트</title>
</head>
<body>
<%
	
	// try-with-resource 구문 : try 블록의 실행이 끝나면 괄호 안에 생성한 자원의 close()를 실행한다.
	// 기존 방식에서는 finally 블록에서 conn.close()를 했었다.
	try (Connection conn = ConnectionProvider.getConnection()) {
		out.println("커넥션 연결 성공");
	} catch(SQLException ex) {
		out.println("커넥션 연결 실패 : " + ex.getMessage());
		application.log("커넥션 연결 실패", ex);
	}
%>
</body>
</html>
