package stocklist.model;

public class StockInfo {
	private String stockCode;
	private String companyName;
	private String basisYear;
	private Integer sales;
	private Integer operatingProfit;
	private Integer netProfit;
	private Integer eps;
	private Double roe;
	public StockInfo(String stockCode, String companyName, String basisYear, Integer sales, Integer operatingProfit, Integer netProfit,
			Integer eps, Double roe) {
		this.stockCode = stockCode;
		this.companyName = companyName;
		this.basisYear = basisYear;
		this.sales = sales;
		this.operatingProfit = operatingProfit;
		this.netProfit = netProfit;
		this.eps = eps;
		this.roe = roe;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBasisYear() {
		return basisYear;
	}
	public void setBasisYear(String basisYear) {
		this.basisYear = basisYear;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getOperatingProfit() {
		return operatingProfit;
	}
	public void setOperatingProfit(Integer operatingProfit) {
		this.operatingProfit = operatingProfit;
	}
	public Integer getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(Integer netProfit) {
		this.netProfit = netProfit;
	}
	public Integer getEps() {
		return eps;
	}
	public void setEps(Integer eps) {
		this.eps = eps;
	}
	public Double getRoe() {
		return roe;
	}
	public void setRoe(Double roe) {
		this.roe = roe;
	}
}
