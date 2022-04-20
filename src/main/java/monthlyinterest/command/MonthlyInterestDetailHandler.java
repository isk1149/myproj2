package monthlyinterest.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import bankbook.model.AccountInfo;
import bankbook.service.AccountInfoService;
import monthlyinterest.model.MonthlyInterestDetail;
import monthlyinterest.service.MonthlyInterestDetailService;
import mvc.command.CommandHandler;
import sql.exception.SelectQueryNoResultException;
import util.AccountNumberDashFormat;
import util.DateFormat;

public class MonthlyInterestDetailHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/monthlyInterestDetail.jsp";
	private AccountInfoService accountInfoService = new AccountInfoService();
	private MonthlyInterestDetailService monthlyInterestDetailService = new MonthlyInterestDetailService();
	

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
		
		User user = (User) req.getSession().getAttribute("authUser");
		String id = user.getId();
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
	
		try {
			AccountInfo accountInfo = accountInfoService.searchAccountInfo(id);
			req.setAttribute("accountInfo", AccountNumberDashFormat.format(accountInfo.getAccountNumber()));
			
			String yearMonth = DateFormat.yearMonthReverseFormatByKorean(req.getParameter("yearMonth"));
			req.setAttribute("yearMonth", req.getParameter("yearMonth"));
			
			ArrayList<MonthlyInterestDetail> monthlyInterestDetailList 
			= monthlyInterestDetailService.searchMonthlyInterestDetail(accountInfo.getBankCode(), accountInfo.getAccountNumber(), yearMonth);
			req.setAttribute("monthlyInterestDetailList", monthlyInterestDetailList);
			System.out.println("111111");
		} catch(SelectQueryNoResultException e) {
			errors.put("selectQueryNoResult", Boolean.TRUE);
		}
		return FORM_VIEW;
	}
	
}
