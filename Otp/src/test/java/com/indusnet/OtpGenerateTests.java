//package com.indusnet;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.indusnet.common.RequestUserModel;
//import com.indusnet.repository.IOtpRepository;
//import com.indusnet.service.IOtpService;
//import com.indusnet.service.impl.OtpServiceImpl;
//import com.indusnet.util.OtpSend;
//import com.indusnet.util.OtpSendToMail;
//
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class OtpGenerateTests {
//
//	@Autowired
//	IOtpService iUserService;
//	
//	 @Autowired
//	 private OtpSend otpSend;
//
//	@MockBean
//	IOtpRepository iUserRepository;
//
//	@Autowired
//	 private OtpSendToMail otpMail;
//	
//	@BeforeEach
//	void setUp(){
//		this.iUserService = new OtpServiceImpl(otpSend,iUserRepository,otpMail);
//	}
//
//	/**
//	 * This Test case for otp generation 
//	 * if otp generate successfully  and otp is 6 digit then test case is pass.
//	 */
//	
//	@Test
//	@Order(1)
//	void otpGenerateTest() {
//		RequestUserModel otpModel = RequestUserModel.builder()
//				.email("arif841236@gmail.com")
//				.mobile("7988354623")
//				.countryCode("+91")
//				.name("Md Arif")
//				.build();
//		String otp = iUserService.generateOtp(otpModel);
//		when(otp.length() == 6).thenReturn(true);
//		assertEquals(6, otp.length());
//	}
//
//	/**
//	 * This Test case for check maximum number of generated otp in one day
//	 * if otp generate 10 times then exceed limit error are throws.
//	 */
//	
//	@Test
//	@Order(2)
//	void otpGenerateMaxTest() {
//
//		RequestUserModel otpModel = RequestUserModel.builder()
//				.email("arif841236@gmail.com")
//				.mobile("7988354623")
//				.countryCode("+91")
//				.name("Md Arif")
//				.build();
//
//		
//		for(int i=0; i<3; i++) {
//			
//			iUserService.generateOtp(otpModel);
//		}
//
//		boolean flag = false;
//		
//		try {
//			iUserService.generateOtp(otpModel);
//		} catch (Exception e) {
//			flag = true;
//		}
//		when(flag).thenReturn(true);
//
//		assertEquals(true, flag);
//	}
//
//}
