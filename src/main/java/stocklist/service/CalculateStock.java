package stocklist.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import stocklist.model.StockInfo;

public class CalculateStock {

	public void calculateStock(ArrayList<StockInfo> stockList, String period, String[] profit) {
		
		Map<String, Boolean> map = new HashMap<>();
		
		// raw data가 2017년부터 있어서 period 최대 4년으로 제한해야 함.
		if (Integer.parseInt(period) > 4) period = "4";
		
		for (int i = 0; i < stockList.size(); i++) {
			 if ((i + 1) % (Integer.parseInt(period) + 1) == 0) continue;
			 
			 StockInfo stockInfo1 = stockList.get(i);
			 StockInfo stockInfo2 = stockList.get(i + 1);
			 for (int j = 0; j < profit.length; j++) {
				 compareProfit(stockInfo1, stockInfo2, profit[j], map);
			 }
		}
		if (map == null || map.size() == 0) return;
		
		for (int i = 0; i < stockList.size(); i++) {
			if (map.containsKey(stockList.get(i).getStockCode())) {
				stockList.remove(i);
				i--;
			}
		}
	}
	
	private void compareProfit(StockInfo stockInfo1, StockInfo stockInfo2, String profit, Map<String, Boolean> map) {
		
		if (map.containsKey(stockInfo1.getStockCode())) return;
		
		switch(profit) {
		case "sales":
			if (stockInfo1.getSales() > stockInfo2.getSales()) 
				map.put(stockInfo1.getStockCode(), Boolean.TRUE);
			break;
		case "operatingprofit":
			if (stockInfo1.getOperatingProfit() > stockInfo2.getOperatingProfit())	
				map.put(stockInfo1.getStockCode(), Boolean.TRUE);
			break;
		case "netprofit":
			if (stockInfo1.getNetProfit() > stockInfo2.getNetProfit())	
				map.put(stockInfo1.getStockCode(), Boolean.TRUE);
			break;
		case "eps":
			if (stockInfo1.getEps() > stockInfo2.getEps())	
				map.put(stockInfo1.getStockCode(), Boolean.TRUE);
			break;
		default :
			break;
		}
	}
}
