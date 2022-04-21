package monthlyinterest.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import bankbook.model.AccountInfo;
import bankbook.service.AccountInfoService;
import monthlyinterest.model.MonthlyInterest;
import monthlyinterest.service.MonthlyInterestService;
import mvc.command.CommandHandler;
import sql.exception.SelectQueryNoResultException;
import util.AccountNumberDashFormat;
import util.DateFormat;
import util.MoneyCommaFormat;

public class MonthlyInterestHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/monthlyInterest.jsp";
	private AccountInfoService accountInfoService = new AccountInfoService();
	private MonthlyInterestService monthlyInterestService = new MonthlyInterestService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSearch(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processSearch(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		User user = (User) req.getSession().getAttribute("authUser");
		String id = user.getId();
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			AccountInfo accountInfo = accountInfoService.searchAccountInfo(id);
			req.setAttribute("accountInfo", AccountNumberDashFormat.format(accountInfo.getAccountNumber()));
			
			MonthlyInterest monthlyInterest 
							= monthlyInterestService.searchMonthlyInterest(accountInfo.getBankCode(), accountInfo.getAccountNumber());
			
			req.setAttribute("monthlyInterest", monthlyInterest);
			
			setMonthlyFormat(req);
			
		} catch(SelectQueryNoResultException e) {
			errors.put("selectQueryNoResult", Boolean.TRUE);
		}
		return FORM_VIEW;
	}
	
	private void setMonthlyFormat(HttpServletRequest req) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("thisMonth", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("oneMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("twoMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("threeMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("fourMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("fiveMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("sixMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("sevenMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("eightMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("nineMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("tenMonthAgo", yyyymm);
        
        cal.add(Calendar.MONTH, -1);
        yyyymm = sdf.format(cal.getTime());
        yyyymm = DateFormat.yearMonthFormatByKorean(yyyymm);
        req.setAttribute("elevenMonthAgo", yyyymm);
	}
}
