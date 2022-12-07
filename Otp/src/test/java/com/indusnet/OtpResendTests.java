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
//class OtpResendTests {
//
//
//
//	@Autowired
//	IOtpService iUserService;
//
//
//	@MockBean
//	IOtpRepository iUserRepository;
//
//	int count = 0;
//
//	@Autowired
//	private OtpSend otpSend;
//
//	@Autowired
//	private OtpSendToMail otpMail;
//
//	@BeforeEach
//	void setUp(){
//		this.iUserService =  new OtpServiceImpl(otpSend,iUserRepository, otpMail);
//	}
//
//
//	/**
//	 * This Test case for check maximum number of resend otp in one day
//	 * if otp generate 5 times then exceed limit error are throws.
//	 */
//
//	@Test
//	void resendTest() {
//
//		RequestUserModel otpModel = RequestUserModel.builder()
//				.email("md970824@gmail.com")
//				.mobile("7988354623")
//				.countryCode("+91")
//				.name("arif")
//				.build();
//		iUserService.generateOtp(otpModel);
//		for(int i = 0; i < 2; i++) {
//
//			iUserService.resend();
//		}
//		boolean flag = false;
//
//		try {
//			iUserService.resend();
//		} catch (Exception e) {
//			flag = true;
//		}
//		when(flag).thenReturn(true);
//
//		assertEquals(true, flag);
//
//	}
//}
