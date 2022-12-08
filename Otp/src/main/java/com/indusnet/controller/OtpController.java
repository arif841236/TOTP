package com.indusnet.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.indusnet.model.OtpData;
import com.indusnet.model.ResendRequest;
import com.indusnet.model.SendOtpRequest;
import com.indusnet.model.ValidateRequest;
import com.indusnet.model.common.OtpSendResponse;
import com.indusnet.model.common.ValidationResponce;
import com.indusnet.service.IOtpService;
import com.indusnet.util.ResponeUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v0.0.1/otp")
@RequiredArgsConstructor
public class OtpController {
	@Autowired
	private IOtpService userService;
	@Autowired
	Gson gson;
	@Autowired
	ResponeUtil responseUtil;

	/*
	 * This Api generate take RequestUserModel
	 * and generate time based otp.
	 * return otp with response OtpResponseMessage
	 */
	/**
	 * 
	 * @param user: This is user store the name , email , and mobile number
	 * @return :its return responceEntity of OtpResponseMessage.
	 */
	@PostMapping("/sendotp")
	public ResponseEntity<OtpSendResponse> sendOtpHandler(@RequestBody @Valid SendOtpRequest user){
		OtpData otpData = userService.generateOtp(user);
		OtpSendResponse responce = responseUtil.response("OTP generated successfully", otpData);
		return ResponseEntity.ok().body(responce);
	}

	/*
	 * This Api validate otp
	 * and return success response OtpResponseMessage
	 */
	/**
	 * 
	 * @param otp : otp is 6 digit string and its validate the user 
	 * @return : if otp is match then its return success response if not match then throws exception.
	 */
	@PostMapping("/validateotp")
	public ResponseEntity<ValidationResponce> verifyOtpHandler(@RequestBody  String validateReq){
		ValidateRequest validateRequest = gson.fromJson(validateReq, ValidateRequest.class);
		ValidationResponce message = userService.validate(validateRequest);
		return ResponseEntity.ok().body(message);
	}

	/*
	 * This Api resend request for otp generate and 
	 * this api use previous RequestUserModel data which call 
	 * in last time of generateOtpHandler api.
	 * and generate time based otp.
	 * return otp with response OtpResponseMessage
	 */
	/**
	 * This Api resend the otp maximum 3 times.
	 * @return : its return success responce then otp generate succesfully 
	 */
	@PostMapping("/resendotp")
	public ResponseEntity<OtpSendResponse> resendOtpHandler(@RequestBody String request){
		ResendRequest resendRequest = gson.fromJson(request, ResendRequest.class);
		OtpData otpData = userService.resend(resendRequest);
		OtpSendResponse responce = responseUtil.response("OTP generated successfully", otpData);
		return ResponseEntity.ok().body(responce);
	}

	/*
	 * This is constructor to initialize the userService and responseUtil
	 */
	/**
	 * 
	 * @param userService :It is service layer and hold all service method.
	 * @param responseUtil :It is util layer and it have otp creation logic and method.
	 */
	public OtpController(IOtpService userService, ResponeUtil responseUtil) {
		super();
		this.userService = userService;
		this.responseUtil = responseUtil;
	}

}
