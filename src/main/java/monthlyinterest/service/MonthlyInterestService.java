package monthlyinterest.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import monthlyinterest.dao.MonthlyInterestDao;
import monthlyinterest.model.MonthlyInterest;
import sql.exception.SelectQueryNoResultException;

public class MonthlyInterestService {
	
	private MonthlyInterestDao monthlyInterestDao = new MonthlyInterestDao();
	
	public MonthlyInterest searchMonthlyInterest(String bankCode, String accountNumber) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			MonthlyInterest monthlyInterest = monthlyInterestDao.searchMonthlyInterest(conn, bankCode, accountNumber);
			if (monthlyInterest == null) {
				throw new SelectQueryNoResultException();
			}
			return monthlyInterest;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
}
