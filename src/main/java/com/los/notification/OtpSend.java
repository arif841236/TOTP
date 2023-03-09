package com.los.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.los.exception.OtpException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class OtpSend {

	@Autowired
	JavaMailSender mailSender;

	private  String accountSid = "AC1e78d14723267e80f82fbec07de6d6ee";
	private  String token = "11bac3c79b2c598e7173b974b50519a1";
	private  String fromNumber = "+15617105720";

	public String send(String body, String mobile) {

		try{
			Twilio.init(accountSid, token);
			Message.creator(new PhoneNumber(mobile), new PhoneNumber(fromNumber), body)		
			.create();
		}
		catch(Exception e){
			throw new OtpException(e.getMessage());
		}
		return accountSid;
	}


	public String sendEmail(String body, String email) {
		SimpleMailMessage sendemail = new SimpleMailMessage();
		try {
			sendemail.setTo(email);
			sendemail.setSubject("Verification");
			sendemail.setText(body);
			sendemail.setCc("arif841236@gmail.com");
			mailSender.send(sendemail);

			return "successfully send the otp ";
		} catch (Exception e) {
			throw new OtpException("Error in email sending");
		}

	}

}
