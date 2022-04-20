package stocklist.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.connection.ConnectionProvider;
import sql.exception.SelectQueryNoResultException;
import stocklist.dao.StockListDao;
import stocklist.model.StockInfo;

public class StockListService {
	
	private StockListDao stockListDao = new StockListDao();

	public ArrayList<StockInfo> stockSearch(String period, String[] profit) {
		
		ArrayList<StockInfo> stockInfoList = null;
		
		try (Connection conn = ConnectionProvider.getConnection()){
			stockInfoList =  stockListDao.select(conn, period);
			if (stockInfoList.size() == 0) {
				throw new SelectQueryNoResultException();
			} 
			// profit 조건에 맞는 기업만 찾기
			CalculateStock calculateStock = new CalculateStock();
			calculateStock.calculateStock(stockInfoList, period, profit);
			
			return stockInfoList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (SelectQueryNoResultException e) {
			throw e;
		}
	}
}
