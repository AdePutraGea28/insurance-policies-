package id.co.putra.insurancepolicy.insurancepolicy.common;

import id.co.putra.insurancepolicy.insurancepolicy.util.Generated;

@Generated
public class Constant {

	private Constant() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Feed me for re-use variable in some class
	 */

	public static final String MSG_SUCCESS = "SUCCESS";
	public static final String MSG_OK = "OK";

	/*
	 * DEFAULT VALUE
	 */
	public static final String STRING_EMPTY = "";
	public static final String STRING_DASH = "-";
	public static final int ZERO_NUMERIC = 0;
	public static final double DV_DOUBLE = 0.0;

	public static final String GENDER_LAKI = "1";
	public static final String GENDER_PEREMPUAN = "2";

	/*
	 * REGEX
	 */
	public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
	public static final String NUMERIC_REGEX = "[0-9]+[\\.]?[0-9]*";

	/*
	 * DATE & TIME
	 */
	public static final String FORMAT_DATE_YYYYMMDD_1 = "yyyy-MM-dd";
	public static final String FORMAT_DATE_YYYYMMDD_2 = "yyyyMMdd";
	public static final String FORMAT_DATE_YYYYMMDD_3 = "yyyy/MM/dd";
	public static final String FORMAT_DATETIME_YYYYMMDD_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATETIME_YYYYMMDD_2 = "yyyyMMddHHmmss";
	public static final String FORMAT_DATETIME_DDMMYYYY_1 = "dd-MM-yyyy HH:mm:ss";
	public static final String FORMAT_DATETIME_DDMMYYYY_2 = "ddMMyyyyHHmmss";
	public static final String FORMAT_DATE_DDMMYYYY_1 = "dd-MM-yyyy";
	public static final String FORMAT_DATE_DDMMYYYY_2 = "ddMMyyyy";
	public static final String FORMAT_DATE_DDMMYYYY_3 = "dd/MM/yyyy";
	public static final String FORMAT_TIME_HHMM = "HH:mm";
	public static final String FORMAT_DATE_YYMMDD = "yyMMdd";

	public static final String ZONE_ASIA_JAKARTA = "Asia/Jakarta";
	public static final String ZONE_ASIA_TAIPEI = "Asia/Taipei";
	public static final String ZONE_ASIA_SEOUL = "Asia/Seoul";
	public static final String ZONE_ZULU = "Asia/Zulu";

	/*
	 * API
	 */
	public static final String HTTP_RESP_CODE_SUCCESS = "200";
	public static final String HTTP_RESP_CODE_CREATED = "201";
	public static final String HTTP_RESP_CODE_BADREQUEST = "400";
	public static final String HTTP_RESP_CODE_UNAUTHORIZED = "401";
	public static final String HTTP_RESP_CODE_FORBIDDEN = "403";
	public static final String HTTP_RESP_CODE_NOTFOUND = "404";

}
