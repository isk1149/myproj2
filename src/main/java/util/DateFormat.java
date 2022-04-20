package util;

public class DateFormat {
	
	public static String yearMonthFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월";
	}
	
	public static String yearMonthDayFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일";
	}
	
	public static String yearMonthDayHourMinuteSecondFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일 " + 
				date.substring(8, 10) + "시 " + date.substring(10, 12) + "분 " + date.substring(12, 14) + "초";
	}
	
	public static String yearMonthReverseFormatByKorean(String date) {
		return date.substring(0, 4) + date.substring(6, 8);
	}
}
