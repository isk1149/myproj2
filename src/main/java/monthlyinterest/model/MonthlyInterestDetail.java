package monthlyinterest.model;

public class MonthlyInterestDetail {

	private String bankCode;
	private String accountNumber;
	private String interestSupplyDate;
	private String interest;
	public MonthlyInterestDetail(String bankCode, String accountNumber, String interestSupplyDate, String interest) {
		super();
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.interestSupplyDate = interestSupplyDate;
		this.interest = interest;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getInterestSupplyDate() {
		return interestSupplyDate;
	}
	public void setInterestSupplyDate(String interestSupplyDate) {
		this.interestSupplyDate = interestSupplyDate;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
}
