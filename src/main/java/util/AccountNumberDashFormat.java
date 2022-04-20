package util;

public class AccountNumberDashFormat {

	public static String format(String accountNumber) {
		return accountNumber.substring(0, 3) + "-" + accountNumber.substring(3, 6) + "-" + accountNumber.substring(6);
	}
}
