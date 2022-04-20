package bankbook.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import bankbook.model.AccountInfo;
import bankbook.model.Bankbook;
import bankbook.model.BankbookInterest;
import bankbook.model.TransactionHistory;
import bankbook.service.AccountInfoService;
import bankbook.service.BankbookInterestService;
import bankbook.service.BankbookSearchService;
import bankbook.service.InterestReceiveNowService;
import bankbook.service.TransactionHistoryService;
import mvc.command.CommandHandler;
import sql.exception.InsertQueryNotApplyException;
import sql.exception.SelectQueryNoResultException;
import sql.exception.UpdateQueryNotApplyException;
import util.AccountNumberDashFormat;
import util.MoneyCommaFormat;

public class BankbookHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/bankbook.jsp";
	private AccountInfoService accountInfoService = new AccountInfoService();
	private BankbookSearchService bankbookSearchService = new BankbookSearchService();	
	private BankbookInterestService bankbookInterestService = new BankbookInterestService();
	private TransactionHistoryService transactionHistoryService = new TransactionHistoryService(); 
	private InterestReceiveNowService interestReceiveNowService = new InterestReceiveNowService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSearch(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSearch(HttpServletRequest req, HttpServletResponse res) throws Exception{
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
		  	Bankbook bankbook = bankbookSearchService.searchbankbook(bankcode, accountnumber, id);
		  	Long deposit = bankbook.getDeposit();
			String depositFormat = MoneyCommaFormat.format(deposit);
			req.setAttribute("depositFormat", depositFormat);
		  	
			
		  	BankbookInterest bankbookInterest = bankbookInterestService.searchBankbookInterest(bankcode, accountnumber, id, 
		  																bankbook.getDeposit(), bankbook.getRate(), bankbook.getOverrate());
		  	int interestApplyDayCount = bankbookInterest.getInterestApplyDayCount();
		  	req.setAttribute("interestApplyDayCount", interestApplyDayCount);
		  	long interest = bankbookInterest.getInterest();
		  	String interestFormat = MoneyCommaFormat.format(interest);
		  	req.setAttribute("interestFormat", interestFormat);
		  	
		  	
		  	ArrayList<TransactionHistory> transactionHistoryList = transactionHistoryService.searchTransactionHistory(bankcode, accountnumber, id);
		  	req.setAttribute("transactionHistoryList", transactionHistoryList);
		  	
		} catch (SelectQueryNoResultException e) {
			errors.put("selectQueryNoResult", Boolean.TRUE);
		} 
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession(false).getAttribute("authUser");
		String id = user.getId();
		
		AccountInfo accountInfo = accountInfoService.searchAccountInfo(id); 
		String bankcode = accountInfo.getBankCode();
	  	String accountnumber = accountInfo.getAccountNumber();
	  	
	  	Bankbook bankbook = bankbookSearchService.searchbankbook(bankcode, accountnumber, id);
	  	BankbookInterest bankbookInterest = bankbookInterestService.searchBankbookInterest(bankcode, accountnumber, id, 
				   													bankbook.getDeposit(), bankbook.getRate(), bankbook.getOverrate());
	  	
	  	if (bankbookInterest.getInterest() != 0L)
	  		interestReceiveNowService.interestReceive(bankcode, accountnumber, id, bankbookInterest.getInterest());
			
		res.sendRedirect(req.getContextPath() + "/bankbook.do");
		return null;
	}
}
