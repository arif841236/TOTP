package com.los.service;

import com.los.model.common.OtpRequestModel;
import com.los.model.common.OtpResponse;
import com.los.model.common.ValidationResponse;

// This interface to take all business logic method with no body
public interface IOtpService {

	public OtpResponse createOtp(OtpRequestModel otpModel); 

	public ValidationResponse validatedOtp(OtpRequestModel otpValidationModel);
}
