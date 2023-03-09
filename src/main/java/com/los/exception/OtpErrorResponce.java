package com.los.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//  This class for showing otp error exception
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtpErrorResponce {
	private Integer status;
	private String message;
}
