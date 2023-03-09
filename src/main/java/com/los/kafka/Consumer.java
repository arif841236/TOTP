package com.los.kafka;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.los.exception.ValidationException;
import com.los.model.NotificationDataBase;
import com.los.model.common.LocalDateTimeConverter;
import com.los.model.common.LoggingResponseMessage;
import com.los.model.common.MessageTypeConst;
import com.los.notification.ITemplateRepository;
import com.los.notification.OtpSend;
import com.los.notification.TemplateModel;
import com.los.repository.INotificationDataRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * This is consumer class for consume message by the topics.
 * @author Md Arif
 *
 */
@Service
@Slf4j
public class Consumer {

	GsonBuilder builder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter()); 
	Gson gson = builder.create();

	@Autowired
	ITemplateRepository iTemplateRepository;

	@Autowired
	OtpSend otpSend;

	@Autowired
	INotificationDataRepository iNotificationDataRepository;

	private KafkaResponse kafkResponse;

	/**
	 * This is method for listen the message from the topic.
	 * @param message
	 */
	@KafkaListener(topics = "newtotp", groupId = "mygroup", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consumeFromTopic(KafkaResponse message) {

		this.kafkResponse = message;

		LoggingResponseMessage logMessageC1 = LoggingResponseMessage.builder()
				.message("Consume kafka message successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(kafkResponse)
				.build();

		log.info(gson.toJson(logMessageC1));
	}

	/**
	 * This method to send otp with help of kafka message.
	 * @param number
	 * @return its return KafkaResponse.
	 */
	public KafkaResponse sendingOTPToMobile(String number) {
		LoggingResponseMessage logMessageC1 = LoggingResponseMessage.builder()
				.message("Kafka response get successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(kafkResponse)
				.build();

		log.info(gson.toJson(logMessageC1));

		if (kafkResponse == null) {
			throw new ValidationException("Kafka response is null");
		}

		List<TemplateModel> template = iTemplateRepository.findByNotificationTypeAndProcessName("OTP",
				kafkResponse.getProcessName());
		log.info(gson.toJson(template));
		if (template.isEmpty()) {
			throw new ValidationException("Template  not found.");
		}

		String str = template.get(template.size() - 1).getTemplateBody().replace("{$$otp$$}", kafkResponse.getOtp())
				.replace("{$$processName$$}", kafkResponse.getProcessName())
				.replace("{$$created_time$$}", kafkResponse.getGeneratedTime().substring(0, 19));

		LoggingResponseMessage logMessageC2 = LoggingResponseMessage.builder()
				.message("Template created successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(str)
				.build();

		log.info(gson.toJson(logMessageC2));

		String mobile = "+".concat(kafkResponse.getCountryCode().concat(kafkResponse.getMobile()));

		LoggingResponseMessage logMessageC3 = LoggingResponseMessage.builder()
				.message("Mobile number get successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(mobile)
				.build();

		log.info(gson.toJson(logMessageC3));

		String sid = null;

		//		sid = otpSend.send(str, mobile);
		LoggingResponseMessage logMessageC4 = LoggingResponseMessage.builder()
				.message("SID number get successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(sid)
				.build();

		log.info(gson.toJson(logMessageC4));

		String emailId = "md970824@gmail.com";
		String res = null;
		 res = otpSend.sendEmail(str, emailId);
		LoggingResponseMessage logMessageC5 = LoggingResponseMessage.builder()
				.message(res)
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(null)
				.build();

		log.info(gson.toJson(logMessageC5));

		String notifiId = UUID.randomUUID().toString().substring(0, 8);
		String eventId = UUID.randomUUID().toString().substring(0, 8);

		NotificationDataBase notificationDataBase = NotificationDataBase.builder()
				.contactChannel("sms")
				.contactDetails(number)
				.externalSystem("Twillio")
				.notificationId(notifiId)
				.notificationMessage(str)
				.externalRefId(sid).sentBy("system")
				.sentOn(LocalDateTime.now())
				.eventId(eventId)
				.status("Success")
				.build();

		NotificationDataBase notificationDataBase2 = iNotificationDataRepository.save(notificationDataBase);

		LoggingResponseMessage logMessageC6 = LoggingResponseMessage.builder()
				.message("Notification data save successfully.")
				.messageTypeId(MessageTypeConst.SUCCESS)
				.data(notificationDataBase2)
				.build();

		log.info(gson.toJson(logMessageC6));

		return kafkResponse;
	}
}
