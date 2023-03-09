package com.los.exception;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.PersistenceException;

import org.postgresql.util.PSQLException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.los.model.common.LocalDateTimeConverter;
import com.los.model.common.LoggingResponseMessage;
import com.los.model.common.MessageTypeConst;
import lombok.extern.slf4j.Slf4j;

//  This class for handle all exception
@ControllerAdvice
@Slf4j
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

	GsonBuilder builder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter()); 
	Gson gson = builder.create();

	@ExceptionHandler(OtpException.class)
	public ResponseEntity<ErrorResponce> userExceptionHandler(OtpException ue, WebRequest wb, Exception e) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.BAD_REQUEST.value())
				.errorMessage(HttpStatus.BAD_REQUEST.name())
				.path(wb.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ue.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ue.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> error = ex.getBindingResult().getFieldErrors();

		OtpErrorResponce otpVException = new OtpErrorResponce(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				error.get(0).getDefaultMessage());

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(error.get(0).getDefaultMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(otpVException, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<OtpErrorResponce> validationExceptionHandler(ValidationException excp) {

		OtpErrorResponce resp = new OtpErrorResponce(HttpStatus.UNPROCESSABLE_ENTITY.value(), excp.getMessage());

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(excp.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(resp)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(SQLException.class)
	public final ResponseEntity<Object> sqlExceptions(SQLException ex, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(error)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(PSQLException.class)
	public final ResponseEntity<Object> handlePSQLException(PSQLException ex,WebRequest request) {
		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(error)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(JsonIOException.class)
	public final ResponseEntity<Object> handleJsonIOException(JsonIOException ex,WebRequest request) {
		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(error)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BeanCreationException.class)
	public final ResponseEntity<Object> handleBeanCreationException(BeanCreationException ex,WebRequest request) {
		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(error)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(PersistenceException.class)
	public final ResponseEntity<Object> handlePersistenceException(PersistenceException ex,WebRequest request) {
		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.messageTypeId(MessageTypeConst.ERROR)
				.data(error)
				.build();

		log.error(gson.toJson(msgStart));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		OtpErrorResponce resp = new OtpErrorResponce(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.NOT_FOUND.value())
				.errorMessage(HttpStatus.NOT_FOUND.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.METHOD_NOT_ALLOWED.value())
				.errorMessage(HttpStatus.METHOD_NOT_ALLOWED.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<Object> nullpointerExceptions(NullPointerException ex, WebRequest request) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorResponce error = ErrorResponce.builder()
				.errorCode(1).status(HttpStatus.NOT_FOUND.value())
				.errorMessage(HttpStatus.NOT_FOUND.name())
				.path(request.getDescription(false).substring(4))
				.timestamp(Timestamp.valueOf(LocalDateTime.now()))
				.traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage())
				.build();

		LoggingResponseMessage logMessageException = LoggingResponseMessage.builder()
				.message(ex.getMessage())
				.data("")
				.messageTypeId(MessageTypeConst.ERROR)
				.build();

		log.error(gson.toJson(logMessageException));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
