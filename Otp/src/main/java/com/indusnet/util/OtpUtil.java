package com.indusnet.util;
import java.util.Base64;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;
@Component
public class OtpUtil {
	public String base64encode(Integer value) {
		byte[] mId = DatatypeConverter.parseHexBinary(value.toString());
		return Base64.getEncoder().encodeToString(mId);
	}
	public int randomValue() {
		Integer messageId = 100000 + Math.abs(new Random().nextInt() * 899900);
		while(messageId > 999999) {
			messageId = messageId / 10;
		}
		return messageId;
	}
}
