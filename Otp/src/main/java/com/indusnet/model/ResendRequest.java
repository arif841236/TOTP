package com.indusnet.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ResendRequest {
	private String type;
	private String typeValue;
	private Integer requeston;
	private String requestdevice;
	private UserModel user;
	private Integer lastOtpId;
}
