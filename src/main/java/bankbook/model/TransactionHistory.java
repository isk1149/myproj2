package bankbook.model;

import java.util.Date;

public class TransactionHistory {
	
	private String bankcode;
	private String accountNumber;
	private Date transactionDate;
	private String transactionDateToChar;
	private String transactionBankcode;
	private String transactionBankName;
	private String transactionAccountNumber;
	private String transactionAccountHolderName;
	private Long transactionAmount;
	private String transactionType;
	public TransactionHistory(String bankcode, String accountNumber, Date transactionDate, String transactionDateToChar,
			String transactionBankcode, String transactionBankName, String transactionAccountNumber,
			String transactionAccountHolderName, Long transactionAmount, String transactionType) {
		super();
		this.bankcode = bankcode;
		this.accountNumber = accountNumber;
		this.transactionDate = transactionDate;
		this.transactionDateToChar = transactionDateToChar;
		this.transactionBankcode = transactionBankcode;
		this.transactionBankName = transactionBankName;
		this.transactionAccountNumber = transactionAccountNumber;
		this.transactionAccountHolderName = transactionAccountHolderName;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
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
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getTransactionDateToChar() {
		return transactionDateToChar;
	}
	public void setTransactionDateToChar(String transactionDateToChar) {
		this.transactionDateToChar = transactionDateToChar;
	}
	public String getTransactionBankcode() {
		return transactionBankcode;
	}
	public void setTransactionBankcode(String transactionBankcode) {
		this.transactionBankcode = transactionBankcode;
	}
	public String getTransactionBankName() {
		return transactionBankName;
	}
	public void setTransactionBankName(String transactionBankName) {
		this.transactionBankName = transactionBankName;
	}
	public String getTransactionAccountNumber() {
		return transactionAccountNumber;
	}
	public void setTransactionAccountNumber(String transactionAccountNumber) {
		this.transactionAccountNumber = transactionAccountNumber;
	}
	public String getTransactionAccountHolderName() {
		return transactionAccountHolderName;
	}
	public void setTransactionAccountHolderName(String transactionAccountHolderName) {
		this.transactionAccountHolderName = transactionAccountHolderName;
	}
	public Long getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
