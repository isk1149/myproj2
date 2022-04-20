package monthlyinterest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import monthlyinterest.model.MonthlyInterestDetail;
import util.DateFormat;
import util.MoneyCommaFormat;

public class MonthlyInterestDetailDao {

	public ArrayList<MonthlyInterestDetail> searchMonthlyInterestDetail(Connection conn, String bankCode, String accountNumber, String yyyymm) 
	throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MonthlyInterestDetail> monthlyInterestDetailList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select bankcode " + 
					                           ", accountnumber " + 
					                           ", to_char(interestsupplydate, 'yyyymmddhh24miss') as interestsupplydate " +
					                           ", interest " + 
					                        "from interesthistory " + 
					                       "where bankcode = ? " + 
					                         "and accountnumber = ? " + 
					                         "and substr(to_char(interestsupplydate, 'yyyymmddhh24miss'), 0, 6) = ? ");
			pstmt.setString(1, bankCode);
			pstmt.setString(2, accountNumber);
			pstmt.setString(3, yyyymm);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				monthlyInterestDetailList.add(new MonthlyInterestDetail(rs.getString("bankcode"),
																		rs.getString("accountnumber"),
																		DateFormat.yearMonthDayFormatByKorean(rs.getString("interestsupplydate")),
																		MoneyCommaFormat.format(rs.getLong("interest"))));
			}
			return monthlyInterestDetailList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
