package com.los.model.common;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class for take input request for otp generate and validate
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OtpRequestModel {
	@NotEmpty(message = "please enter operation type")
	private String operationType;
	@Size(min = 1, max = 11, message = "please enter valid otp")
	@Pattern(regexp = "\\d+",message = "please enter only numeric value in generatedOtp")
	private String generatedOtp;
	@NotEmpty(message = "please enter channel")
	private String channel;
	@NotEmpty(message = "please enter start date")
	private String startTime;
	@Size(min = 1, max = 5, message = "please enter valid duration")
	@Pattern(regexp = "\\d+",message = "please enter only numeric value in duration")
	@NotEmpty(message = "please enter duration time")
	private String duration;
	@Size(min = 1, max = 2, message = "please enter valid digit otp")
	@Pattern(regexp = "\\d+",message = "please enter only numeric value in digit")
	@NotEmpty(message = "please enter digit")
	private String digit;
	@Size(min = 10, max = 10, message = "please enter valid mobile number")
	@Pattern(regexp = "(0|91)?[6-9]\\d{9}", message = "please enter valid mobile number")
	@NotEmpty(message = "please enter mobile number")
	private String mobile;
	@Pattern(regexp = "\\d+",message = "please enter only numeric value in country code")
	@NotEmpty(message = "please enter country code")
	private String countryCode;
	@NotEmpty(message = "please enter process name")
	private String processName;
	private String otpId;
}
