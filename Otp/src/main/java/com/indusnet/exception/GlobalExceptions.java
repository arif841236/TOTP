package com.indusnet.exception;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * This class for handle all exception 
 * and its annotate with @ControllerAdvice
 */
@ControllerAdvice
public class GlobalExceptions {
	/**
	 * 
	 * @param ue : it is OtpException
	 * @param wb : it is web request
	 * @param e : it is Exception error class.
	 * @return : its return responseEntity of OtpErrorMessage.
	 */
	@ExceptionHandler(OtpException.class)
	public ResponseEntity<ErrorResponce> userExceptionHandler(
			OtpException ue,WebRequest wb,Exception e){
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();
		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.BAD_REQUEST.value())
				.errorMessage(HttpStatus.BAD_REQUEST.name())
				.path(wb.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(stackTrace)
				.build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param nValid :its argument of validate.
	 * @return : its return response entity of Map.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validationExceptionMessage(
			MethodArgumentNotValidException nValid){
		Map<String, String> msgList = new HashMap<>();
		nValid.getBindingResult().getFieldErrors()
		.forEach(error -> msgList.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(msgList, HttpStatus.BAD_REQUEST);
	}
}
