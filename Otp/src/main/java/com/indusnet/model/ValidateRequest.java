package com.indusnet.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ValidateRequest {
	private Integer otp;
	private Integer otpId;
	private Integer requeston;
	private String requestdevice;
}
