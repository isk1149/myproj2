package bankbook.model;

import java.util.Date;

public class Bankbook {
	private String bankCode;
	private String accountNumber;
	private String accountHolder;
	private Date createDate;
	private Long deposit;
	private Double rate;
	private Double overrate;
	public Bankbook(String bankCode, String accountNumber, String accountHolder, Date createDate, Long deposit,
			Double rate, Double overrate) {
		super();
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.createDate = createDate;
		this.deposit = deposit;
		this.rate = rate;
		this.overrate = overrate;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getDeposit() {
		return deposit;
	}
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getOverrate() {
		return overrate;
	}
	public void setOverrate(Double overrate) {
		this.overrate = overrate;
	}
}
