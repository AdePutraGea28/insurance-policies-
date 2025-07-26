package id.co.putra.insurancepolicy.insurancepolicy.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PutraCustomException extends Exception {

	/**
	 * Generate Serial Version ID Manually
	 */
	private static final long serialVersionUID = -8202046082219631699L;

	private HttpStatus statusCode;

	public PutraCustomException(String message, HttpStatus statusCode, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public PutraCustomException(String message) {
		super(message);
	}

}
