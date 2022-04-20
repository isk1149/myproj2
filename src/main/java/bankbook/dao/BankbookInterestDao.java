package bankbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankbook.model.BankbookInterest;
import jdbc.JdbcUtil;

public class BankbookInterestDao {

	public BankbookInterest select(Connection conn,  String bankcode, String accountnumber, String id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BankbookInterest bankbookInterest = null;
		try {
			pstmt = conn.prepareStatement("select b.* " + 
										    "from accountInfo a, " + 
											     "bankbookinterest b " + 
										   "where a.bankcode = ? " + 
											 "and a.accountnumber = ? " + 
										     "and a.accountholder = ? " + 
											 "and a.bankcode = b.bankcode " + 
										     "and a.accountnumber = b.accountnumber ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bankbookInterest = new BankbookInterest(rs.getString("bankcode"),
														rs.getString("accountnumber"),
														rs.getInt("interestapplydaycount"),
														rs.getLong("interest"));
			}
			return bankbookInterest;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public boolean checkInterestHistoryEmpty(Connection conn,  String bankcode, String accountnumber, String id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean empty = false;
		try {
			pstmt = conn.prepareStatement("select count(*) " + 
										    "from accountinfo a, " + 
					                             "interesthistory b " + 
					                       "where a.bankcode = ? " + 
					                         "and a.accountnumber = ? " + 
					                         "and a.accountholder = ? " + 
					                         "and a.bankcode = b.bankcode " + 
											 "and a.accountnumber = b.accountnumber ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 0)
					empty = true;
			}
			return empty;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int calculateFirstInterestDayCount(Connection conn,  String bankcode, String accountnumber, String id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int interestDayCount = 0;
		
		try {
			pstmt = conn.prepareStatement("select floor(to_date(to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') - to_date(to_char(b.transactiondate, 'yyyymmdd'), 'yyyymmdd')) " + 
										    "from accountinfo a, " + 
					                             "transactionhistory b " + 
					                       "where a.bankcode = ? " + 
					                         "and a.accountnumber = ? " + 
					                         "and a.accountholder = ? " + 
					                         "and a.bankcode = b.bankcode " + 
											 "and a.accountnumber = b.accountnumber " + 
											 "and b.transactiontype = '입금' " + 
											 "and rownum = 1 " + 
											 "order by b.transactiondate ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) 
				interestDayCount = rs.getInt(1);
			return interestDayCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int calculateInterestDayCount(Connection conn,  String bankcode, String accountnumber, String id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int interestDayCount = 0;
		try {
			pstmt = conn.prepareStatement("select floor(to_date(to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') - to_date(to_char(b.interestsupplydate, 'yyyymmdd'), 'yyyymmdd')) " + 
										    "from accountinfo a, " + 
					                             "interesthistory b " + 
					                       "where a.bankcode = ? " + 
					                         "and a.accountnumber = ? " + 
					                         "and a.accountholder = ? " + 
					                         "and a.bankcode = b.bankcode " + 
											 "and a.accountnumber = b.accountnumber " +
					                         "and rownum = 1 " +
					                         "order by b.interestsupplydate desc ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) 
				interestDayCount = rs.getInt(1);
			return interestDayCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int searchInterestApplyDayCount(Connection conn,  String bankcode, String accountnumber) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int interestApplyDayCount = 0;
		try {
			pstmt = conn.prepareStatement("select interestapplydaycount from bankbookinterest where bankcode = ? and accountnumber = ? ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			rs = pstmt.executeQuery();
			if (rs.next()) 
				interestApplyDayCount = rs.getInt("interestapplydaycount");
			return interestApplyDayCount;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int updateInterestData(Connection conn,  String bankcode, String accountnumber, String id, int updateApplyCount, long interest) 
		 throws SQLException {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("update bankbookinterest " + 
										     "set interestapplydaycount = interestapplydaycount + ?, " + 
										         "interest = interest + ? " + 
										   "where bankcode = ? "
										   + "and accountnumber = ? ");
			pstmt.setInt(1, updateApplyCount);
			pstmt.setLong(2, interest);
			pstmt.setString(3, bankcode);
			pstmt.setString(4, accountnumber);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int interestReceiveToBankbook(Connection conn, String bankcode, String accountnumber, String id, long interest) 
		throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update bankbook " + 
											 "set deposit = deposit + ? " + 
										   "where bankcode = ? " + 
											 "and accountnumber = ? " + 
										     "and accountholder = ? ");
			pstmt.setLong(1, interest);
			pstmt.setString(2, bankcode);
			pstmt.setString(3, accountnumber);
			pstmt.setString(4, id);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertInterestDataToInterestHistory(Connection conn, String bankcode, String accountnumber, long interest, String date) 
		throws SQLException {
			
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into interesthistory " + 
											 "values(?, ?, to_date(?, 'YYYYMMDDHH24MISS'), ?) ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, date);
			pstmt.setLong(4, interest);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	/*
	public int insertInterestDataToTransactionHistory(Connection conn, String bankcode, String accountnumber, String id, long interest, String date) 
		throws SQLException {
			
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into transactionhistory " + 
											 "values(?, ?, to_date(?, 'YYYYMMDDHH24MISS'), ?, ?, ?, ?) ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, date);
			pstmt.setString(4, bankcode);
			pstmt.setString(5, "0000000001");
			pstmt.setLong(6, interest);
			pstmt.setString(7, "I");
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	*/
	public int initializeBankbookInterest(Connection conn, String bankcode, String accountnumber) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update bankbookinterest " +
											 "set interestapplydaycount = ?, interest = ? " +
										   "where bankcode = ? and accountnumber = ? ");
			pstmt.setInt(1, 0);
			pstmt.setLong(2, 0L);
			pstmt.setString(3, bankcode);
			pstmt.setString(4, accountnumber);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
public BankbookInterest test(Connection conn,  String bankcode, String accountnumber, String id) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BankbookInterest bankbookInterest = null;
		try {
			pstmt = conn.prepareStatement("select b.* " + 
										    "from accountInfo a, " + 
											     "bankbookinterest b " + 
										   "where a.bankcode = ? " + 
											 "and a.accountnumber = ? " + 
										     "and a.accountholder = ? " + 
											 "and a.bankcode = b.bankcode " + 
										     "and a.accountnumber = b.accountnumber ");
			pstmt.setString(1, bankcode);
			pstmt.setString(2, accountnumber);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if (rs.next()) 
				bankbookInterest = new BankbookInterest(rs.getString("bankcode"),
														rs.getString("accountnumber"),
														rs.getInt("interestapplydaycount"),
														rs.getLong("interest"));
			return bankbookInterest;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}
