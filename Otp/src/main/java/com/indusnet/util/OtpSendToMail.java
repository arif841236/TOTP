package com.indusnet.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class OtpSendToMail {
	@Autowired
	private JavaMailSender javaMailSender;
	public void sendEmail(String email, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("For OTP validation ");
		msg.setText(message);
		javaMailSender.send(msg);
	}
}
