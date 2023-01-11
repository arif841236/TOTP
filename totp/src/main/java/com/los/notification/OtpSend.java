package com.los.notification;

import org.springframework.stereotype.Component;
import com.los.exception.OtpException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Component
public class OtpSend {

	private  String accountSid = "AC1e78d14723267e80f82fbec07de6d6ee";
	private  String token = "e0eed58b076af4865bb6251b000746ea";
	private  String fromNumber = "+15617105720";
	
//	private  String accountSid = "AC14d442dc7967dd42cd0bedcf99fd404b";
//	private  String token = "7307bf4d8cb777e0bc4eb1ae1bfee981";
//	private  String fromNumber = "+16509107946";
	
	public String send(String body, String mobile) {
		try{
			Twilio.init(accountSid, token);
			Message.creator(new PhoneNumber(mobile)
					, new PhoneNumber(fromNumber), body)
			.create();
		}
		catch(Exception e){
			throw new OtpException(e.getMessage());
		}
	  return accountSid;
	}

}
