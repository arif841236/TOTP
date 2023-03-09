package com.los.model.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class Message {

	@Value("${otp.generated}")
	String generated;

	@Value("${otp.verified}")
	String verified;

	@Value("${otp.failed}")
	String failed;

	@Value("${otp.createdby}")
	String createdby;

	@Value("${otp.generate.msg}")
	String generatedMessage;

	@Value("${otp.validate.msg}")
	String validateMesssage;

	@Value("${otp.expire.msg}")
	String expiryMessage;

	@Value("${otp.invalid.msg}")
	String invalidMessage;

	@Value("${otp.exceed.verified.attempt.msg}")
	String verifiedExceedMessage;

	@Value("${otp.verifieds.before.msg}")
	String verfiedBeforeMsg;

	@Value("${otp.notgenerated.msg}")
	String notGeneratedMessage;

	@Value("${otp.datetime.formate.msg}")
	String dateFormateMessage;

	@Value("${otp.operationtype.msg}")
	String operationTypeMessage;

	@Value("${otp.generate.exceed.msg}")
	String limitExceedMessage;
}
