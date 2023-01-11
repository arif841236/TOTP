package com.los.kafka;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.los.exception.ValidationException;
import com.los.model.NotificationDataBase;
import com.los.notification.ITemplateRepository;
import com.los.notification.OtpSend;
import com.los.notification.TemplateModel;
import com.los.repository.INotificationDataRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {
	@Autowired
	Gson gson;
	@Autowired
	ITemplateRepository iTemplateRepository;
	@Autowired
	OtpSend otpSend;
	@Autowired
	INotificationDataRepository iNotificationDataRepository;

	private KafkaResponse kafkResponse;

	@KafkaListener(topics = "testTopic", groupId = "mygroup", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consumeFromTopic(ConsumerRecord<Integer, String> message) {
		this.kafkResponse = gson.fromJson(message.value(), KafkaResponse.class);
		log.info("message" + message);
	}

	public KafkaResponse sendingOTPToMobile(String number) {
		log.info("inside kafka " + kafkResponse);
		List<TemplateModel> template = iTemplateRepository.findByNotificationTypeAndProcessName("OTP",
				kafkResponse.getProcessName());
		if(template.isEmpty()) {
			throw new ValidationException("Template  not found.");
		}
		String str = template.get(template.size()-1).getTemplateBody().replace("{$$otp$$}", kafkResponse.getOtp())
				.replace("{$$processName$$}", kafkResponse.getProcessName())
				.replace("{$$created_time$$}", kafkResponse.getGeneratedTime().substring(0, 19));
		log.info(str);
//		String sid = otpSend.send(str, number);
		String sid = null;
		log.info("sid is " + sid);
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
				.status("Success").build();
		NotificationDataBase notificationDataBase2 = iNotificationDataRepository.save(notificationDataBase);
		log.info("Notification data is " + notificationDataBase2);
		return kafkResponse;
	}
}
