package stocklist.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import sql.exception.SelectQueryNoResultException;
import stocklist.model.StockInfo;
import stocklist.service.StockListService;

public class StockListHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/stockList.jsp";
	private StockListService stockListService = new StockListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		} 
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String period = req.getParameter("period"); 
		String[] profit = req.getParameterValues("profit");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
	
		if (period == null || period.equals("") || profit == null) {
			errors.put("noYearOrProfitCheck", Boolean.TRUE);
			return FORM_VIEW;
		}

		try {
			ArrayList<StockInfo> stockInfoList = stockListService.stockSearch(period, profit);
			req.setAttribute("stockInfoList", stockInfoList);
			req.setAttribute("period", period);
		} catch (SelectQueryNoResultException e) {
			errors.put("noResult", Boolean.TRUE);
		}
		return FORM_VIEW;
	}
}
