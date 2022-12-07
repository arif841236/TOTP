package com.indusnet.model.common;


import lombok.Builder;
import lombok.Getter;

/**
 * This class for success response message;
 */
@Builder
@Getter
public class OtpSendResponse {

	private Integer status;
	private String message ;
	private String type;
	private String otpFor;
	private Integer validupto;
	private String messageId;
	
}
