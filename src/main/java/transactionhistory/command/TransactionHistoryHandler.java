package transactionhistory.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import bankbook.model.AccountInfo;
import bankbook.service.AccountInfoService;
import mvc.command.CommandHandler;
import sql.exception.SelectQueryNoResultException;
import transactionhistory.model.TransactionHistory;
import transactionhistory.service.TransactionHistoryService;
import util.AccountNumberDashFormat;

public class TransactionHistoryHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/transactionHistory.jsp";
	private TransactionHistoryService transactionHistoryService = new TransactionHistoryService();
	private AccountInfoService accountInfoService = new AccountInfoService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSearch(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSearch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession(false).getAttribute("authUser");
		String id = user.getId();
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			AccountInfo accountInfo = accountInfoService.searchAccountInfo(id); 
			String accountNumber = AccountNumberDashFormat.format(accountInfo.getAccountNumber());
			req.setAttribute("accountNumber", accountNumber);
			
			String bankcode = accountInfo.getBankCode();
		  	String accountnumber = accountInfo.getAccountNumber();
			ArrayList<TransactionHistory> transactionHistoryList = transactionHistoryService.searchTransactionHistory(bankcode, accountnumber, id);
		  	req.setAttribute("transactionHistoryList", transactionHistoryList);

		} catch (SelectQueryNoResultException e) {
			errors.put("selectQueryNoResult", Boolean.TRUE);
		} 
		return FORM_VIEW;
	}
	
}
