package com.los.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.los.model.common.OtpRequestModel;
import com.los.model.common.OtpResponse;
import com.los.model.common.ValidationResponce;
import com.los.service.IOtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * This is controller class to take all mapping
 */
@RestController
@RequestMapping("/v1/otp")
@Slf4j
@Api(description = "TOTP Api",tags= {"TOTP"})
public class OtpController {
	/**
	 * Initialize the service layer.
	 */
	@Autowired
	IOtpService otpService;

	/**
	 * This method to generate and validate otp.
	 */
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Otp Generate or Validate successfully "),
			@ApiResponse(code = 201, message = "Otp Generate"),
			@ApiResponse(code = 401, message = "You are not authorized to generate otp"),
			@ApiResponse(code = 403, message = "Accessing the generate otp you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not authorized to generate otp") })
	@ApiOperation(value="Generate otp",tags = "Validate otp",notes = "Send otp to the user",response = OtpResponse.class)
	@PostMapping("/")
	public ResponseEntity<Object> generateOtp(@RequestBody @Valid OtpRequestModel requestParameter) {
		log.info("model is " + requestParameter);
		if (requestParameter.getOperationType().equalsIgnoreCase("Generate")) {
			OtpResponse responce = otpService.createOtp(requestParameter);
			log.info("Successfully run");
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} else {
			ValidationResponce message = otpService.validatedOtp(requestParameter);
			log.info("Successfully run");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
	}
}
