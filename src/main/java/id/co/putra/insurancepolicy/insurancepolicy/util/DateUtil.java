package id.co.putra.insurancepolicy.insurancepolicy.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import id.co.putra.insurancepolicy.insurancepolicy.common.Constant;

public class DateUtil {

	private DateUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Feed me with processing date (re-use method)
	 */

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp getCurrentTimestampGMT7() {
//		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(Constant.ZONE_ASIA_JAKARTA)).truncatedTo(ChronoUnit.SECONDS);
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(Constant.ZONE_ASIA_JAKARTA));
		return Timestamp.valueOf(zdt.toLocalDateTime());
	}

	public static String formatTimestampYMD(Timestamp timestamp) {
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(Constant.FORMAT_DATE_YYMMDD);
		return outputDateFormat.format(timestamp);
	}

}
