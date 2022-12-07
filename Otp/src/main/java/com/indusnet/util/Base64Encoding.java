package com.indusnet.util;

import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;
@Component
public class Base64Encoding {

	public String encode(Integer value) {
		byte[] mId = DatatypeConverter.parseHexBinary(value.toString());
		return Base64.getEncoder().encodeToString(mId);
	}
}
