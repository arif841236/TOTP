package com.indusnet.util;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.indusnet.model.OtpData;
import com.indusnet.model.common.OtpSendResponse;

/**
 * This is ResponceUtil class 
 * its have one method response 
 *
 */
@Service
public class ResponeUtil {
	/**
	 * 
	 * @param message: this is responce message
	 * @param otp : it is generated otp 
	 * @return : its return OtpResponseMessage
	 */
	public OtpSendResponse response(String message,OtpData otpData) {
		return OtpSendResponse.builder().message(message)
				.messageId(otpData.getMessageId())
				.status(HttpStatus.OK.value())
				.otpFor(otpData.getTypeValue())
				.type(otpData.getType().name().toLowerCase())
				.validupto(otpData.getValidupto().getNano())
				.build();
	}
}
