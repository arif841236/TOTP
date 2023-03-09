package com.los.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.los.model.common.LoggingResponseMessage;
import com.los.model.common.MessageTypeConst;
import com.los.model.common.OtpRequestModel;
import com.los.model.common.OtpResponse;
import com.los.service.IOtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


//  This is controller class to take all mapping
@RestController
@Slf4j
@Api(description = "TOTP API v1",value = "Validate", tags = { "TOTP"})
public class OtpController {

	// Initialize the service layer.
	@Autowired
	IOtpService otpService;

	@Autowired
	Gson gson;

	// This method to generate and validate otp.

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OTP Generate or Validate successfully "),
			@ApiResponse(code = 401, message = "You are not authorized to generate OTP"),
			@ApiResponse(code = 403, message = "forbidden"),
			@ApiResponse(code = 404, message = "Not authorized to generate OTP") })
	@ApiOperation(value = "Generate OTP and validate OTP", notes = "Send OTP to the user", response = OtpResponse.class)
	@ApiImplicitParam(name = "requestParameter", value = "Fill the OTP request model details",required = true,dataType = "OtpRequestModel")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value= "/")
	public ResponseEntity<Object> generateOtp(@RequestBody @Valid OtpRequestModel requestParameter) {

		LoggingResponseMessage msgStart = LoggingResponseMessage.builder()
				.message("OTP generation method started.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(requestParameter)
				.build();

		log.info(gson.toJson(msgStart));

		Object response = null;

		if (requestParameter.getOperationType().equalsIgnoreCase("Generate")) {

			response = otpService.createOtp(requestParameter);
			LoggingResponseMessage logResponse = LoggingResponseMessage.builder()
					.message("OTP is successfully generated.")
					.messageTypeId(MessageTypeConst.SUCCESS)
					.data(response)
					.build();

			log.info(gson.toJson(logResponse));

		} else {

			response = otpService.validatedOtp(requestParameter);

			LoggingResponseMessage logResponse = LoggingResponseMessage.builder()
					.message("OTP is successfully verified.")
					.messageTypeId(MessageTypeConst.SUCCESS)
					.data(response)
					.build();

			log.info(gson.toJson(logResponse));
		}
		LoggingResponseMessage msgEnd = LoggingResponseMessage.builder()
				.message("OTP generation method end.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.build();

		log.info(gson.toJson(msgEnd));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
