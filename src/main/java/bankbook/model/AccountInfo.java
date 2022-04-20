package bankbook.model;

public class AccountInfo {
	private String bankCode;
	private String accountNumber;
	private String accountHolder;
	
	public AccountInfo(String bankCode, String accountNumber, String accountHolder) {
		super();
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
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
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
}
