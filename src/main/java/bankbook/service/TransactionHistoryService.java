package bankbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bankbook.dao.TransactionHistoryDao;
import bankbook.model.TransactionHistory;
import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;

public class TransactionHistoryService {

	private TransactionHistoryDao transactionHistoryDao = new TransactionHistoryDao();
	
	public ArrayList<TransactionHistory> searchTransactionHistory(String bankcode, String accountnumber, String id) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			ArrayList<TransactionHistory> transactionHistoryList = transactionHistoryDao.select(conn, bankcode, accountnumber, id);
			if (transactionHistoryList == null) {
				throw new SelectQueryNoResultException();
			}
			return transactionHistoryList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
}
