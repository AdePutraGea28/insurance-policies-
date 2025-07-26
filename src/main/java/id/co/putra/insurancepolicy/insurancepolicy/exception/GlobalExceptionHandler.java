package id.co.putra.insurancepolicy.insurancepolicy.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import id.co.putra.insurancepolicy.insurancepolicy.common.Constant;
import id.co.putra.insurancepolicy.insurancepolicy.dto.GlobalErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * @param ex
	 * @param request
	 * @return logs exception and sends response related to AdiraCustomException
	 *         class
	 */
	@ExceptionHandler(PutraCustomException.class)
	public ResponseEntity<GlobalErrorResponseDTO> putraExceptionHandler(PutraCustomException ex, WebRequest request) {
		log.error("Error from the api: {}", ex);
		return ResponseEntity.status(ex.getStatusCode()).body(new GlobalErrorResponseDTO(ex.getStatusCode().getReasonPhrase(), ex.getCause().getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GlobalErrorResponseDTO> notValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
		log.error("Validation error: {}", ex.getMessage());

		// Ambil semua pesan error dari binding result
		List<String> errors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(err -> err.getField() + ": " + err.getDefaultMessage())
			.collect(Collectors.toList());

		// Gabungkan semua pesan jadi satu string
		String errorMessage = String.join("; ", errors);

		GlobalErrorResponseDTO<?> response = new GlobalErrorResponseDTO(
			HttpStatus.BAD_REQUEST.name(),
			"Validation failed",
			errorMessage
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
