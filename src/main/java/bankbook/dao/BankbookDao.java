package bankbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankbook.model.Bankbook;
import jdbc.JdbcUtil;

public class BankbookDao {

	public Bankbook select(Connection conn, String bankcode, String accountnumber, String id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bankbook bankbook = null;
		try {
			pstmt = conn.prepareStatement("select b.* " + 
											"from accountinfo a, bankbook b " + 
										   "where a.bankcode = ? " + 
										   	 "and a.accountnumber = ? " + 
										   	 "and a.accountholder = ? " + 
										     "and a.bankcode = b.bankcode " + 
										     "and a.accountnumber = b.accountnumber " + 
										     "and a.accountholder = b.accountholder");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bankbook = new Bankbook(rs.getString("bankcode"),
										rs.getString("accountnumber"),
										rs.getString("accountholder"),
										rs.getDate("createdate"),
										rs.getLong("deposit"),
										rs.getDouble("rate"),
										rs.getDouble("overrate"));
			}
			return bankbook;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateDeposit(Connection conn, String bankcode, String accountnumber, String id, int plusMinus, long transactionAmount) throws SQLException {
		
		PreparedStatement pstmt = null;
		Bankbook bankbook = null;
		int updateCount = 0;
		try {
			pstmt = conn.prepareStatement("update bankbook " + 
											"set deposit = deposit + ? " + 
										   "where bankcode = ? " + 
										   	 "and accountnumber = ? " + 
										   	 "and accountholder = ? ");
			pstmt.setLong(1, plusMinus * transactionAmount);
			pstmt.setString(2, bankcode);
			pstmt.setString(3, accountnumber);
			pstmt.setString(4, id);
			updateCount = pstmt.executeUpdate();
			return updateCount;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
