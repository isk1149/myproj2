package bankbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankbook.model.AccountInfo;
import jdbc.JdbcUtil;

public class AccountInfoDao {
	
	public AccountInfo select(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountInfo accountInfo = null;
		try {
			pstmt = conn.prepareStatement("select * from accountinfo where bankcode = ? and accountholder = ? and main = ? ");
			pstmt.setString(1, "000001");
			pstmt.setString(2, trim(id));
			pstmt.setString(3, "Y");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				accountInfo = new AccountInfo(rs.getString("bankcode"),
												rs.getString("accountnumber"),
												rs.getString("accountholder"));
			}
			return accountInfo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public AccountInfo selectByAccountNumber(Connection conn, String bankcode, String accountNumber) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountInfo accountInfo = null;
		try {
			pstmt = conn.prepareStatement("select * from accountinfo where bankcode = ? and accountnumber = ? and main = ?");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, trim(accountNumber));
			pstmt.setString(3, "Y");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				accountInfo = new AccountInfo(rs.getString("bankcode"),
												rs.getString("accountnumber"),
												rs.getString("accountholder"));
			}
			return accountInfo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
