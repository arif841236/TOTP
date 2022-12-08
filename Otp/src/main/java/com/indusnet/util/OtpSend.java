package com.indusnet.util;
import com.indusnet.exception.OtpException;
import com.indusnet.model.common.UserDetail;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class OtpSend {
	@Autowired
	private SimpMessagingTemplate webSocket;
	private  String accountSid = "AC1e78d14723267e80f82fbec07de6d6ee";
	private  String token = "718fa868f1268ab97ca2278edf4eeba9";
	private  String fromNumber = "+15617105720";
	public void send(UserDetail user) {
		try{
			Twilio.init(accountSid, token);
			Message.creator(new PhoneNumber(user.getMobile())
					, new PhoneNumber(fromNumber), user.getMessage())
			.create();
		}
		catch(Exception e){
			webSocket.convertAndSend("/api/v1.1.2/otp/generate", getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
			throw new OtpException(e.getMessage());
		}
		webSocket.convertAndSend("/api/v1.1.2/otp/generate", getTimeStamp() + ": SMS has been sent!: "+user.getMobile());
	}
	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}
}
