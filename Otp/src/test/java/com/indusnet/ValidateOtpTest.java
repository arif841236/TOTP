//package com.indusnet;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.BeforeEach;
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
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class ValidateOtpTest {
//
//	@Autowired
//	IOtpService iUserService;
//
//	@MockBean
//	IOtpRepository iUserRepository;
//	
//	 @Autowired
//	 private OtpSend otpSend;
//
//	 @Autowired
//	 private OtpSendToMail otpMail;
//	
//	@BeforeEach
//	void setUp(){
//		this.iUserService =  new OtpServiceImpl(otpSend,iUserRepository, otpMail);
//	}
//	
//
//	/**
//	 * This Test case for check validate or not  
//	 */
//	@Test
//	void validateTest() {
//		RequestUserModel otpModel = RequestUserModel.builder()
//				.email("arif841236@gmail.com")
//				.mobile("7988354623")
//				.countryCode("+91")
//				.name("Md Arif")
//				.build();
//		String otp = iUserService.generateOtp(otpModel);
//		String message = iUserService.validate(otp);
//		when(message.length() > 0).thenReturn(true);
//		assertEquals(6, otp.length());
//
//	}
//	
//
//	/**
//	 * This Test case for check maximum number of validate otp in one day
//	 * if otp generate 4 times then exceed limit error are throws.
//	 */
//	
//	@Test
//	void maximumAttemptTest() {
//		
//		RequestUserModel otpModel = RequestUserModel.builder()
//				.email("arif841236@gmail.com")
//				.mobile("7988354623")
//				.countryCode("+91")
//				.name("Md Arif")
//				.build();
//		String otp = iUserService.generateOtp(otpModel);
//		for(int i = 0; i < 3; i++) {
//			
//			iUserService.validate(otp);
//		}
//		
//     boolean flag = false;
//		
//		try {
//			iUserService.validate(otp);
//		} catch (Exception e) {
//			flag = true;
//		}
//		when(flag).thenReturn(true);
//
//		assertEquals(true, flag);
//		
//	}
//
//}
