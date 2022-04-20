package transactionhistory.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;
import transactionhistory.dao.TransactionHistoryDao;
import transactionhistory.model.TransactionHistory;

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
			throw new RuntimeException(e);
		}
	}
}
