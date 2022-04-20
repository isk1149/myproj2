package remittance.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import bankbook.model.AccountInfo;
import bankbook.model.BankCode;
import bankbook.model.Bankbook;
import bankbook.service.AccountInfoService;
import bankbook.service.BankCodeService;
import bankbook.service.BankbookSearchService;
import member.model.Member;
import member.service.MemberService;
import mvc.command.CommandHandler;
import sql.exception.SelectQueryNoResultException;

public class RemittanceHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/remittance.jsp";
	private AccountInfoService accountInfoService = new AccountInfoService();
	private BankbookSearchService bankbookSearchService = new BankbookSearchService();
	private MemberService memberService = new MemberService();
	private BankCodeService bankCodeService = new BankCodeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return FORM_VIEW;
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		} 
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User) req.getSession(false).getAttribute("authUser");
		
		String[] bankcode = req.getParameterValues("bankcode");
		String accountNumber = req.getParameter("accountNumber");
		
		String transactionAmountString = req.getParameter("transactionAmount");
		if (transactionAmountString == null || transactionAmountString.equals("")) {
			errors.put("notNumber", Boolean.TRUE);
			return FORM_VIEW;
		}
		
		long transactionAmount = Long.parseLong(req.getParameter("transactionAmount"));
		
		if (bankcode == null || accountNumber == null || (accountNumber.trim()).equals("")) {
			errors.put("noBankCodeOrAccountNumber", Boolean.TRUE);
			return FORM_VIEW;
		}
		
		try {
			AccountInfo transactionAccountInfo = accountInfoService.searchAccountInfoByAccountNumber(bankcode[0], accountNumber);
			if (transactionAccountInfo == null) {
				errors.put("noBankCodeOrAccountNumber", Boolean.TRUE);
				return FORM_VIEW;
			} else if (transactionAccountInfo.getAccountHolder().equals(user.getId())){
				errors.put("myAccountNumber", Boolean.TRUE);
				return FORM_VIEW;
			}
			
			Member transactionMember = memberService.searchMember(transactionAccountInfo.getAccountHolder());
			BankCode transactionBankCode = bankCodeService.searchBankName(transactionAccountInfo.getBankCode());
			
			AccountInfo myAccountInfo = accountInfoService.searchAccountInfo(user.getId());
			BankCode myBankCode = bankCodeService.searchBankName(myAccountInfo.getBankCode());
			
			Bankbook myBankbook = bankbookSearchService.searchbankbook(myAccountInfo.getBankCode(), myAccountInfo.getAccountNumber(), 
																	 myAccountInfo.getAccountHolder());
			if (transactionAmount > myBankbook.getDeposit()) {
				errors.put("insufficientDeposit", Boolean.TRUE);
				return FORM_VIEW;
			}
			
			req.setAttribute("user", user);
			req.setAttribute("myAccountInfo", myAccountInfo);
			req.setAttribute("myBankCode", myBankCode);
			
			req.setAttribute("transactionMember", transactionMember);
			req.setAttribute("transactionAccountInfo", transactionAccountInfo);
			req.setAttribute("transactionBankCode", transactionBankCode);
			
			req.setAttribute("transactionAmount", transactionAmount);
			
			return "/WEB-INF/view/remittanceProcessing.jsp";
		} catch (SelectQueryNoResultException e) {
			errors.put("selectQueryNoResult", Boolean.TRUE);
			return FORM_VIEW;
		} 
	}
}
