package com.los;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.los.kafka.Consumer;
import com.los.kafka.Producer;
import com.los.model.common.LocalDateTimeConverter;
import com.los.model.common.Message;
import com.los.model.common.OtpRequestModel;
import com.los.model.common.OtpResponse;
import com.los.model.common.ValidationResponse;
import com.los.repository.IOtpRepository;
import com.los.service.IOtpService;
import com.los.service.impl.OtpServiceImpl;
import com.los.util.Util;

@SpringBootTest
class TotpApplicationTests {

	@Autowired
	IOtpService iOtpService;

	//	@MockBean
	@Autowired
	IOtpRepository otpRepository;

	@Autowired
	Util util;

	GsonBuilder builder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter()); 
	Gson gson = builder.create();

	@Autowired
	Message msg;

	@Autowired
	Producer producer;

	@Autowired
	Consumer consumer;

	@BeforeEach
	void setUp() {
		this.iOtpService = new OtpServiceImpl(otpRepository, util,msg,producer, consumer);
	}

	@Test
	@Order(1)
	void otpGenerateTest() {
		OtpRequestModel otpModel = OtpRequestModel.builder()
				.channel("app")
				.digit("6")
				.duration("180")
				.mobile("6070223312")
				.operationType("generate")
				.startTime("2022-12-18 12:43:47")
				.countryCode("91")
				.processName("PAYMENTS")
				.build();

		OtpResponse otpResponse = iOtpService.createOtp(otpModel);
		assertEquals(HttpStatus.OK.value(), otpResponse.getStatus());
	}

	@Test
	@Order(2)
	void otpValidateTest() {
		OtpRequestModel otpModel = OtpRequestModel.builder()
				.channel("app")
				.digit("6")
				.duration("180")
				.mobile("6070223311")
				.operationType("generate")
				.startTime("2022-12-18 12:43:47")
				.countryCode("91")
				.processName("PAYMENTS")
				.build();

		OtpResponse otpResponse = iOtpService.createOtp(otpModel);

		OtpRequestModel otpModel2 = OtpRequestModel.builder()
				.channel("app")
				.digit("6")
				.generatedOtp(otpResponse.getOtp())
				.duration("180")
				.mobile("6070223311")
				.operationType("validate")
				.startTime("2022-12-18 12:43:47")
				.countryCode("91")
				.processName("PAYMENTS")
				.otpId(otpResponse.getOtpId())
				.build();

		ValidationResponse vaResponce = iOtpService.validatedOtp(otpModel2);
		assertEquals(HttpStatus.OK.value(), vaResponce.getStatus());
	}

}
