package id.co.putra.insurancepolicy.insurancepolicy.dto;

import org.apache.commons.lang3.time.FastDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.putra.insurancepolicy.insurancepolicy.common.Constant;
import id.co.putra.insurancepolicy.insurancepolicy.util.DateUtil;
import id.co.putra.insurancepolicy.insurancepolicy.util.ValueUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalSuccessResponseDTO<T> {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("dateTime")
	private String dateTime = FastDateFormat.getInstance(Constant.FORMAT_DATETIME_YYYYMMDD_1)
			.format(DateUtil.getCurrentTimestamp());

	@JsonProperty("data")
	private T data;

	public GlobalSuccessResponseDTO() {
	}

	public GlobalSuccessResponseDTO(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public GlobalSuccessResponseDTO(String status, String message, T data) {
		super();
		if (Boolean.TRUE.equals(ValueUtil.hasValue(message))) {
			this.message = message;
			this.status = status;
		}
		this.data = data;
	}

}
