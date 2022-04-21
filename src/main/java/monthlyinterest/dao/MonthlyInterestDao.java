package monthlyinterest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import monthlyinterest.model.MonthlyInterest;
import util.MoneyCommaFormat;

public class MonthlyInterestDao {

	public MonthlyInterest searchMonthlyInterest(Connection conn, String bankCode, String accountNumber) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MonthlyInterest monthlyInterest = null;
		try {
			pstmt = conn.prepareStatement("select bankcode " + 
											   ", accountnumber " + 
											   ", nvl(sum(case when trunc(sysdate, 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as thismonth " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -1), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as onemonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -2), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as twomonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -3), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as threemonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -4), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as fourmonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -5), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as fivemonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -6), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as sixmonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -7), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as sevenmonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -8), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as eightmonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -9), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as ninemonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -10), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as tenmonthago " + 
											   ", nvl(sum(case when trunc(add_months(sysdate, -11), 'month') = trunc(interestsupplydate, 'month') then interest end), 0) as elevenmonthago " + 
										    "from interesthistory " + 
										   "where bankcode = ? " + 
										     "and accountnumber = ? " + 
										"group by bankcode " + 
										       ", accountnumber  ");
			pstmt.setString(1, bankCode);
			pstmt.setString(2, accountNumber);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				monthlyInterest = new MonthlyInterest(rs.getString("bankcode"),
														    rs.getString("accountnumber"),
														    MoneyCommaFormat.format(rs.getLong("thismonth")),
														    MoneyCommaFormat.format(rs.getLong("onemonthago")),
														    MoneyCommaFormat.format(rs.getLong("twomonthago")),
														    MoneyCommaFormat.format(rs.getLong("threemonthago")),
														    MoneyCommaFormat.format(rs.getLong("fourmonthago")),
														    MoneyCommaFormat.format(rs.getLong("fivemonthago")),
														    MoneyCommaFormat.format(rs.getLong("sixmonthago")),
														    MoneyCommaFormat.format(rs.getLong("sevenmonthago")),
														    MoneyCommaFormat.format(rs.getLong("eightmonthago")),
														    MoneyCommaFormat.format(rs.getLong("ninemonthago")),
														    MoneyCommaFormat.format(rs.getLong("tenmonthago")),
														    MoneyCommaFormat.format(rs.getLong("elevenmonthago"))
													  );
			}
			return monthlyInterest;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}
