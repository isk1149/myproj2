package bankbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import bankbook.dao.BankCodeDao;
import bankbook.model.BankCode;
import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;

public class BankCodeService {
	private BankCodeDao bankCodeDao = new BankCodeDao();
	
	public BankCode searchBankName(String bankCode) {
		BankCode bankCodeObj = null;
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			bankCodeObj = bankCodeDao.select(conn, bankCode);
			if (bankCodeObj == null)
				throw new SelectQueryNoResultException();
			return bankCodeObj;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
}
