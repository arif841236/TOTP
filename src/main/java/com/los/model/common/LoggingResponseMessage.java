package com.los.model.common;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoggingResponseMessage {

	private HttpStatus statusCode;
	private String message;
	private Object data;
	private MessageTypeConst messageTypeId;
}
