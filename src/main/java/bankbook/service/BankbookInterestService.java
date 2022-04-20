package bankbook.service;

import java.sql.Connection;
import java.sql.SQLException;

import bankbook.dao.BankbookInterestDao;
import bankbook.model.BankbookInterest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;

public class BankbookInterestService {
	
	private BankbookInterestDao bankbookInterestDao = new BankbookInterestDao();
	
	public BankbookInterest searchBankbookInterest(String bankcode, String accountNumber, String id, Long deposit, Double rate, Double overrate) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			calculateInterest(conn, bankcode, accountNumber, id, deposit, rate, overrate);			
			
			conn.commit();
			
			BankbookInterest bankbookInterest = bankbookInterestDao.select(conn, bankcode, accountNumber, id);
			if (bankbookInterest == null) { 
				JdbcUtil.rollback(conn);
				throw new SelectQueryNoResultException();
			}
			return bankbookInterest;
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}  catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
	
	private void calculateInterest(Connection conn, String bankcode, String accountNumber, String id, Long deposit, Double rate, Double overrate) 
		  throws SQLException {
		
		boolean empty = false;
		// 이자 지급 히스토리에서 지급 이력 있는지 확인
		empty = bankbookInterestDao.checkInterestHistoryEmpty(conn, bankcode, accountNumber, id);
		
		int interestDayCount = 0;
		if (empty) {
			// 이자 지급 히스토리에서 지급 이력이 없으면 계좌 거래내역에서 해당 예금 계좌에 최초로 돈이 들어온 날짜부터 오늘까지 몇 일 지났는지 날짜 계산
			interestDayCount = bankbookInterestDao.calculateFirstInterestDayCount(conn, bankcode, accountNumber, id);
		} else {
			// 이자 지급 히스토리에서 최신 날짜 가져와서 오늘까지 몇일 지났는지 날짜 계산
			interestDayCount = bankbookInterestDao.calculateInterestDayCount(conn, bankcode, accountNumber, id);
		}
		//test(deposit, rate, overrate, 1);
		// 하루가 지나기 전엔 이자 계산X
		if (interestDayCount == 0) return;
		// bankbookinterest 테이블( 지급할 이자가 계산되는 테이블 )에서 이자적용일수를 가져옴
		// 이자적용일수와 interestDayCount가 같다는 것은 bankbookinterest 테이블에 새로운 이자를 계산할 필요가 없으므로 리턴함.
		// 이자 적용을 bankbook.jsp 페이지를 요청할 때 하게끔 되어 있기 때문에 있는 조건문임.
		int interestApplyCount = bankbookInterestDao.searchInterestApplyDayCount(conn, bankcode, accountNumber);
		if (interestApplyCount == interestDayCount) return ;
		
		// 마지막으로 이자 받은 날부터 현재까지 몇 일 지났는지 계산한 interestDayCount에서
		// 현재 bankbookinterest 테이블에 계산된 이자적용일수를 빼야 최종 업데이트할 일수가 나옴
		int updateApplyCount = interestDayCount - interestApplyCount;
		
		//이자 계산
		long interest = calculateInterest(deposit, rate, overrate, updateApplyCount);
		int updateCount = bankbookInterestDao.updateInterestData(conn, bankcode, accountNumber, id, updateApplyCount, interest);
		if (updateCount == 0) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}
	}
	
	private long calculateInterest(long deposit, double rate, double overrate, int interestDayCount) {
		if (deposit > 100000000L) {
			return (long)((100000000L * (rate / 100) / 365 * interestDayCount) + 
						 ((deposit - 100000000L) * (overrate / 100) / 365 * interestDayCount));
		}
		return (long)(deposit * (rate / 100) / 365 * interestDayCount);
	}
	/*
	private long test(long deposit, double rate, double overrate, int interestDayCount) {
		
		if (deposit > 100000000L) {
			System.out.println((long)(100000000L * (rate / 100) / 365 * interestDayCount) + " / " + (long)((deposit - 100000000L) * (overrate / 100) / 365 * interestDayCount));
			System.out.println(100000000L * (rate / 100));
			System.out.println(100000000L * (rate / 100) / 365);
			System.out.println(100000000L * (rate / 100) / 365 * interestDayCount);
			System.out.println((long)(100000000L * (rate / 100) / 365 * interestDayCount));
			return (long)(100000000L * (rate / 100) / 365 * interestDayCount) + 
				   (long)((deposit - 100000000L) * (overrate / 100) / 365 * interestDayCount);
		}
		return (long)(deposit * (rate / 100) / 365 * interestDayCount);
	}
	*/
}
