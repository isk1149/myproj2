package remittance.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import bankbook.dao.BankbookDao;
import transactionhistory.dao.TransactionHistoryDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import sql.exception.UpdateQueryNotApplyException;

public class RemittanceService {

	private BankbookDao bankbook = new BankbookDao();
	private TransactionHistoryDao transactionHistory = new TransactionHistoryDao();
	
	public void remit(String myBankCode, String myAccountNumber, String myAccountHolder, 
					  String transactionBankCode, String transactionAccountNumber, String transactionAccountHolder, long transactionAmount) {
		Connection conn = null;
		int updateCount = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			updateCount = bankbook.updateDeposit(conn, myBankCode, myAccountNumber, myAccountHolder, -1, transactionAmount);
			if (updateCount == 0) {
				JdbcUtil.rollback(conn);
				throw new UpdateQueryNotApplyException();
			}
			updateCount = bankbook.updateDeposit(conn, transactionBankCode, transactionAccountNumber, transactionAccountHolder, 1, transactionAmount);
			if (updateCount == 0) {
				JdbcUtil.rollback(conn);
				throw new UpdateQueryNotApplyException();
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = sdf.format(new java.util.Date());
			
			updateCount = transactionHistory.insertDataToTransactionHistory(conn, myBankCode, myAccountNumber, date, 
															transactionBankCode, transactionAccountNumber, transactionAmount, "출금");
			if (updateCount == 0) {
				JdbcUtil.rollback(conn);
				throw new UpdateQueryNotApplyException();
			}
			
			updateCount = transactionHistory.insertDataToTransactionHistory(conn, transactionBankCode, transactionAccountNumber, date, 
															myBankCode, myAccountNumber, transactionAmount, "입금");
			if (updateCount == 0) {
				JdbcUtil.rollback(conn);
			throw new UpdateQueryNotApplyException();
			}
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (UpdateQueryNotApplyException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}  finally {
			JdbcUtil.close(conn);
		}
	}
	
}
