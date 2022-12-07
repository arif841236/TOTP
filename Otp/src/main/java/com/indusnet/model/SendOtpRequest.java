package com.indusnet.model;

import com.indusnet.util.EmailOrPhone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendOtpRequest {

	private String type;
	@EmailOrPhone
	private String typeValue;
	private Integer requeston;
	private String requestdevice;
	private UserModel user;

}
