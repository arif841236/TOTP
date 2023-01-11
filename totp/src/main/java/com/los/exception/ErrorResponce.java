package com.los.exception;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;

/**
 * This class for error message;
 */
@Builder
@Getter
public class ErrorResponce {
	private Timestamp timestamp;
	private Integer status;
	private Integer errorCode;
	private String errorMessage;
	private Long traceID;
	private String errorDetails;
	private String path;
}
