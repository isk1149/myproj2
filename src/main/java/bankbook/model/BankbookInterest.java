package bankbook.model;

public class BankbookInterest {
    private String bankcode;
    private String accountNumber;
    private int interestApplyDayCount;
    private Long interest;
	public BankbookInterest(String bankcode, String accountNumber, int interestApplyDayCount, Long interest) {
		super();
		this.bankcode = bankcode;
		this.accountNumber = accountNumber;
		this.interestApplyDayCount = interestApplyDayCount;
		this.interest = interest;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getInterestApplyDayCount() {
		return interestApplyDayCount;
	}
	public void setInterestApplyDayCount(int interestApplyDayCount) {
		this.interestApplyDayCount = interestApplyDayCount;
	}
	public Long getInterest() {
		return interest;
	}
	public void setInterest(Long interest) {
		this.interest = interest;
	}
}
