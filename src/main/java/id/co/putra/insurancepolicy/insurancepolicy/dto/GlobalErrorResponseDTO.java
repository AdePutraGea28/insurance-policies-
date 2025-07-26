package id.co.putra.insurancepolicy.insurancepolicy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import id.co.putra.insurancepolicy.insurancepolicy.util.ValueUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalErrorResponseDTO<T> {

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("error")
	private String error;

	public GlobalErrorResponseDTO() {
	}

	public GlobalErrorResponseDTO(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public GlobalErrorResponseDTO(String status, String message, String error) {
		super();
		if (Boolean.TRUE.equals(ValueUtil.hasValue(error))) {
			this.status = status;
			this.error = error;
		}
		this.message = message;
	}

}
