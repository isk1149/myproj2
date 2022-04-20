package bankbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import bankbook.dao.AccountInfoDao;
import bankbook.model.AccountInfo;
import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;

public class AccountInfoService {

	AccountInfoDao accountInfoDao = new AccountInfoDao();
	
	public AccountInfo searchAccountInfo(String id) {
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			AccountInfo accountInfo = accountInfoDao.select(conn, id);
			if (accountInfo == null)
				throw new SelectQueryNoResultException();
			return accountInfo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
	
	public AccountInfo searchAccountInfoByAccountNumber(String bankcode, String accountNumber) {
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			AccountInfo accountInfo = accountInfoDao.selectByAccountNumber(conn, bankcode, accountNumber);
			if (accountInfo == null)
				throw new SelectQueryNoResultException();
			return accountInfo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
	
}
