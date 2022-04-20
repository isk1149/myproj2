package monthlyinterest.service;

import java.sql.Connection;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import monthlyinterest.dao.MonthlyInterestDetailDao;
import monthlyinterest.model.MonthlyInterestDetail;
import sql.exception.SelectQueryNoResultException;

public class MonthlyInterestDetailService {

	private MonthlyInterestDetailDao monthlyInterestDetailDao = new MonthlyInterestDetailDao();
	
	public ArrayList<MonthlyInterestDetail> searchMonthlyInterestDetail(String bankCode, String accountNumber, String yyyymm) throws Exception {
		
		try (Connection conn = ConnectionProvider.getConnection()){
			ArrayList<MonthlyInterestDetail> monthlyInterestDetailList = 
					monthlyInterestDetailDao.searchMonthlyInterestDetail(conn, bankCode, accountNumber, yyyymm);
			if (monthlyInterestDetailList == null)
				throw new SelectQueryNoResultException();
			return monthlyInterestDetailList;
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
}
