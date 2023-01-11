package com.los.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class for showing OTP generate response;
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OtpResponse {
	private Integer status;
	private String message;
	private String otp;
	private String otpDurationTime;
	private String otpId;
}
