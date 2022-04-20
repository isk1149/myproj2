package bankbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankbook.model.AccountInfo;
import bankbook.model.BankCode;
import jdbc.JdbcUtil;

public class BankCodeDao {
	
	public BankCode select(Connection conn, String bankCode) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BankCode bankCodeObj = null;
		try {
			pstmt = conn.prepareStatement("select * from bankcode where bankcode = ? ");
			pstmt.setString(1, bankCode);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bankCodeObj = new BankCode(rs.getString("bankcode"), rs.getString("bankname"));
			}
			return bankCodeObj;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
