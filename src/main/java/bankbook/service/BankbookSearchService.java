package bankbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import bankbook.dao.BankbookDao;
import bankbook.model.Bankbook;
import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;

public class BankbookSearchService {

	private BankbookDao bankbookDao = new BankbookDao();
	
	public Bankbook searchbankbook(String bankcode, String accountnumber, String id) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Bankbook bankbook = bankbookDao.select(conn, bankcode, accountnumber, id);
			if (bankbook == null)
				throw new SelectQueryNoResultException();
			return bankbook;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
}
