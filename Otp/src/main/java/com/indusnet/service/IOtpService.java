package com.indusnet.service;

import com.indusnet.exception.OtpException;
import com.indusnet.model.OtpData;
import com.indusnet.model.ResendRequest;
import com.indusnet.model.SendOtpRequest;
import com.indusnet.model.ValidateRequest;
import com.indusnet.model.common.ValidationResponce;

/**
 * This Interface have 3 method generateOtp,validateOtp, and resendOtp
 * and also throws an OtpException.
 *
 */

public interface IOtpService {
	public OtpData generateOtp(SendOtpRequest user) throws OtpException;
	public ValidationResponce validate(ValidateRequest validRequest) throws OtpException;
	public OtpData resend(ResendRequest resRequest) throws OtpException;
}
