package com.los.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.los.exception.OtpException;
import java.io.IOException;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JSONObjectConverter implements AttributeConverter<String[], String> {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(String[] meta) {
		
		try {
		
			return objectMapper.writeValueAsString(meta);
		
		} catch (JsonProcessingException ex) {
			
			throw new OtpException("Error with json converter");
		
		}
	}

	@Override
	public String[] convertToEntityAttribute(String dbData) {
		
		try {
			
			return objectMapper.readValue(dbData, String[].class);
		
		} catch (IOException ex) {
			
			throw new OtpException("Error with json converter");
		
		}
	}
}
