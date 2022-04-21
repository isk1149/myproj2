package transactionhistory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import transactionhistory.model.TransactionHistory;

public class TransactionHistoryDao {

	public ArrayList<TransactionHistory> select(Connection conn, String bankcode, String accountNumber, String id) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TransactionHistory> transactionHistoryList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select b.bankcode, b.accountnumber, b.transactiondate, " +
												 "to_char(b.transactiondate,'yyyy-mm-dd hh24:mi:ss') as transactiondatetochar, " +
												 "b.transactionbankcode, c.bankname, b.transactionaccountnumber, " + 
												 "e.name, b.transactionamount, b.transactiontype " + 
											"from accountinfo a, " + 
												 "transactionhistory b, " + 
												 "bankcode c, " + 
												 "accountinfo d, " + 
												 "member e " + 
										   "where a.bankcode = ? " + 
										    "and a.accountnumber = ? " + 
										    "and a.accountholder = ? " + 
										    "and a.bankcode = b.bankcode " + 
										    "and a.accountnumber = b.accountnumber " + 
										    "and b.transactionbankcode = c.bankcode " + 
										    "and b.transactionbankcode = d.bankcode " + 
											"and b.transactionaccountnumber = d.accountnumber " + 
											"and d.accountholder = e.id " + 
										    "order by b.transactiondate desc ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountNumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TransactionHistory transactionHistory = new TransactionHistory(rs.getString("bankcode"),
																			   rs.getString("accountnumber"),
																			   rs.getDate("transactiondate"),
																			   rs.getString("transactiondatetochar"),
																			   rs.getString("transactionbankcode"),
																			   rs.getString("bankname"),
																			   rs.getString("transactionaccountnumber"),
																			   rs.getString("name"),
																			   rs.getLong("transactionamount"),
																			   rs.getString("transactiontype"));
				if (transactionHistory.getTransactionAccountNumber().equals("0000000001"))
					transactionHistory.setTransactionAccountHolderName("이자");
				
				transactionHistoryList.add(transactionHistory);
			}
			return transactionHistoryList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertDataToTransactionHistory(Connection conn, String myBankcode, String myAccountnumber, String date, 
			   									String transactionBankCode, String transactionAccountNumber, long interest, String inOut) 
		throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into transactionhistory " + 
				 "values(?, ?, to_date(?, 'YYYYMMDDHH24MISS'), ?, ?, ?, ?) ");
			pstmt.setString(1, myBankcode);
			pstmt.setString(2, myAccountnumber);
			pstmt.setString(3, date);
			pstmt.setString(4, transactionBankCode);
			pstmt.setString(5, transactionAccountNumber);
			pstmt.setLong(6, interest);
			pstmt.setString(7, inOut);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
