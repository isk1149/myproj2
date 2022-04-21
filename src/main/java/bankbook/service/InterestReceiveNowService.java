package bankbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import bankbook.dao.BankbookInterestDao;
import transactionhistory.dao.TransactionHistoryDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import sql.exception.InsertQueryNotApplyException;
import sql.exception.UpdateQueryNotApplyException;

public class InterestReceiveNowService {
	
	private BankbookInterestDao bankbookInterestDao = new BankbookInterestDao();
	private TransactionHistoryDao transactionHistoryDao = new TransactionHistoryDao();
	
	// 1. bankbook에 반영
	// 2. interesthistory에 반영
	// 3. transactionhistory에 반영
	// 4. bankbookinterest 초기화
	// 5. 한번에 커밋
	public void interestReceive(String bankcode, String accountNumber, String id, long interest) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			bankbookInterestDao.interestReceiveToBankbook(conn, bankcode, accountNumber, id, interest);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = sdf.format(new java.util.Date());
			int count = 0;
			
			count = bankbookInterestDao.insertInterestDataToInterestHistory(conn, bankcode, accountNumber, interest, date);
			if (count == 0) {
				JdbcUtil.rollback(conn);
				throw new InsertQueryNotApplyException();
			}
			count = 0;
			
			count = transactionHistoryDao.insertDataToTransactionHistory(conn, bankcode, accountNumber, date, "000001", "0000000001", interest, "입금");
			if (count == 0) {
				JdbcUtil.rollback(conn); 
				throw new InsertQueryNotApplyException();
			}
			count = 0;
			
			count = bankbookInterestDao.initializeBankbookInterest(conn, bankcode, accountNumber);
			if (count == 0) {
				JdbcUtil.rollback(conn);
				throw new UpdateQueryNotApplyException();
			}
			count = 0;
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (InsertQueryNotApplyException | UpdateQueryNotApplyException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
