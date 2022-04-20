package monthlyinterest.model;

public class MonthlyInterest {
	private String bankCode;
	private String accountNumber;
	private Long thisMonthInterest;
	private Long oneMonthAgoInterest;
	private Long twoMonthAgoInterest;
	private Long threeMonthAgoInterest;
	private Long fourMonthAgoInterest;
	private Long fiveMonthAgoInterest;
	private Long sixMonthAgoInterest;
	private Long sevenMonthAgoInterest;
	private Long eightMonthAgoInterest;
	private Long nineMonthAgoInterest;
	private Long tenMonthAgoInterest;
	private Long elevenMonthAgoInterest;
	public MonthlyInterest(String bankCode, String accountNumber, Long thisMonthInterest, Long oneMonthAgoInterest,
			Long twoMonthAgoInterest, Long threeMonthAgoInterest, Long fourMonthAgoInterest, Long fiveMonthAgoInterest,
			Long sixMonthAgoInterest, Long sevenMonthAgoInterest, Long eightMonthAgoInterest, Long nineMonthAgoInterest,
			Long tenMonthAgoInterest, Long elevenMonthAgoInterest) {
		super();
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.thisMonthInterest = thisMonthInterest;
		this.oneMonthAgoInterest = oneMonthAgoInterest;
		this.twoMonthAgoInterest = twoMonthAgoInterest;
		this.threeMonthAgoInterest = threeMonthAgoInterest;
		this.fourMonthAgoInterest = fourMonthAgoInterest;
		this.fiveMonthAgoInterest = fiveMonthAgoInterest;
		this.sixMonthAgoInterest = sixMonthAgoInterest;
		this.sevenMonthAgoInterest = sevenMonthAgoInterest;
		this.eightMonthAgoInterest = eightMonthAgoInterest;
		this.nineMonthAgoInterest = nineMonthAgoInterest;
		this.tenMonthAgoInterest = tenMonthAgoInterest;
		this.elevenMonthAgoInterest = elevenMonthAgoInterest;
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
	public Long getThisMonthInterest() {
		return thisMonthInterest;
	}
	public void setThisMonthInterest(Long thisMonthInterest) {
		this.thisMonthInterest = thisMonthInterest;
	}
	public Long getOneMonthAgoInterest() {
		return oneMonthAgoInterest;
	}
	public void setOneMonthAgoInterest(Long oneMonthAgoInterest) {
		this.oneMonthAgoInterest = oneMonthAgoInterest;
	}
	public Long getTwoMonthAgoInterest() {
		return twoMonthAgoInterest;
	}
	public void setTwoMonthAgoInterest(Long twoMonthAgoInterest) {
		this.twoMonthAgoInterest = twoMonthAgoInterest;
	}
	public Long getThreeMonthAgoInterest() {
		return threeMonthAgoInterest;
	}
	public void setThreeMonthAgoInterest(Long threeMonthAgoInterest) {
		this.threeMonthAgoInterest = threeMonthAgoInterest;
	}
	public Long getFourMonthAgoInterest() {
		return fourMonthAgoInterest;
	}
	public void setFourMonthAgoInterest(Long fourMonthAgoInterest) {
		this.fourMonthAgoInterest = fourMonthAgoInterest;
	}
	public Long getFiveMonthAgoInterest() {
		return fiveMonthAgoInterest;
	}
	public void setFiveMonthAgoInterest(Long fiveMonthAgoInterest) {
		this.fiveMonthAgoInterest = fiveMonthAgoInterest;
	}
	public Long getSixMonthAgoInterest() {
		return sixMonthAgoInterest;
	}
	public void setSixMonthAgoInterest(Long sixMonthAgoInterest) {
		this.sixMonthAgoInterest = sixMonthAgoInterest;
	}
	public Long getSevenMonthAgoInterest() {
		return sevenMonthAgoInterest;
	}
	public void setSevenMonthAgoInterest(Long sevenMonthAgoInterest) {
		this.sevenMonthAgoInterest = sevenMonthAgoInterest;
	}
	public Long getEightMonthAgoInterest() {
		return eightMonthAgoInterest;
	}
	public void setEightMonthAgoInterest(Long eightMonthAgoInterest) {
		this.eightMonthAgoInterest = eightMonthAgoInterest;
	}
	public Long getNineMonthAgoInterest() {
		return nineMonthAgoInterest;
	}
	public void setNineMonthAgoInterest(Long nineMonthAgoInterest) {
		this.nineMonthAgoInterest = nineMonthAgoInterest;
	}
	public Long getTenMonthAgoInterest() {
		return tenMonthAgoInterest;
	}
	public void setTenMonthAgoInterest(Long tenMonthAgoInterest) {
		this.tenMonthAgoInterest = tenMonthAgoInterest;
	}
	public Long getElevenMonthAgoInterest() {
		return elevenMonthAgoInterest;
	}
	public void setElevenMonthAgoInterest(Long elevenMonthAgoInterest) {
		this.elevenMonthAgoInterest = elevenMonthAgoInterest;
	}
}
