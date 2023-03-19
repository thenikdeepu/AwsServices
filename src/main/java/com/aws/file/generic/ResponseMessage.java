package com.aws.file.generic;

public class ResponseMessage {

	public static final String ERROR_CODE = "ERR0500";

	public static final String ERROR_CODE_MESSAGE = "Oops something went wrong, please try again after sometime.";

	public static final String OTP_GENERATE_EXCEED_MSG = "Your request is temporaily blocked,Try after 30 minutes.";

	public static final String SUCCESS_API_CODE = "SUC0200";

	public static final String SUCCESS_API_MSG = "Success";

	public static final String OTP_EXPIRED_MSG = "OTP is expired.";

	public static final String INVALID_OTP_MSG = "Invalid OTP.Please enter a valid OTP.";

	public static final String USER_TEMPORAILY_BLOCKED = "Your account is freezed for next 2 hours.Please login after 2 hours";

	public static final String MOBILE_NUMBER_REGEX = "^[6-9][0-9]{9}$";

}
