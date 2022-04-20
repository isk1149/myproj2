package stocklist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import jdbc.JdbcUtil;
import stocklist.model.StockInfo;

public class StockListDao {
	
	public ArrayList<StockInfo> select(Connection conn, String period) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		ArrayList<StockInfo> stockInfoList = new ArrayList<>();
		StockInfo stockInfo = null;
		try {
			pstmt = conn.prepareStatement("select a.stockcode, b.companyname, a.basisyear, a.sales, a.operatingprofit, a.netprofit, a.eps, a.roe " 
										+ "from stockinfo a, corporation b "
										+ "where a.stockcode = b.stockcode and a.basisyear between ? and ? "
										+ "order by a.stockcode, a.basisyear");
			pstmt.setString(1, String.valueOf(nowYear - Integer.parseInt(period) - 1));
			pstmt.setString(2, String.valueOf(nowYear - 1));
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				stockInfo = new StockInfo(rs.getString("stockcode"),
										  rs.getString("companyname"),
						  				  rs.getString("basisyear"),
						  				  rs.getInt("sales"),
						  				  rs.getInt("operatingprofit"),
						  				  rs.getInt("netprofit"),
						  				  rs.getInt("eps"),
						  				  rs.getDouble("roe"));
				
				stockInfoList.add(stockInfo);
			}
			return stockInfoList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
