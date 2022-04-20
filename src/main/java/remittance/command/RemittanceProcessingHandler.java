package remittance.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import remittance.service.RemittanceService;

public class RemittanceProcessingHandler implements CommandHandler{

	private RemittanceService remittanceService = new RemittanceService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		} 
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String myBankCode = req.getParameter("myBankCode");
		String myAccountNumber = req.getParameter("myAccountNumber");
		String myAccountHolder = req.getParameter("myAccountHolder");
		
		String transactionBankCode = req.getParameter("transactionBankCode");
		String transactionAccountNumber = req.getParameter("transactionAccountNumber");
		String transactionAccountHolder = req.getParameter("transactionAccountHolder");
		
		String transactionAmountString = req.getParameter("transactionAmount");
		long transactionAmount = Long.parseLong(transactionAmountString);
		
		// 1. bankbook 변화( 송금, 수신 둘 다 )
		// 2. transactiohistory In, Out
		remittanceService.remit(myBankCode, myAccountNumber, myAccountHolder, 
								transactionBankCode, transactionAccountNumber, transactionAccountHolder, transactionAmount);

		res.sendRedirect(req.getContextPath() + "/bankbook.do");
		return null;
	}
}
