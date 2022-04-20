package util;

public class MoneyCommaFormat {
	public static String format(long money) {
		return String.format("%,d", money);
	}
}
