package com.los.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 * This is producer class for produce message on topics.
 * @author Md Arif
 *
 */
@Service
@Slf4j
public class Producer {

	@Autowired
	Gson gson;

	public static final String TOPIC = "newtotp";

	@Autowired 
	private KafkaTemplate<String, Object> kafkaTemp;

	public void publishToTopic(Object message) {

		log.info(gson.toJson(TOPIC));

		ListenableFuture<SendResult<String, Object>> future = this.kafkaTemp.send(TOPIC, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onSuccess(SendResult<String, Object> result) {
				log.info("Message [{}] delivered with offset {}",
						message,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.warn("Unable to deliver message [{}]. {}", 
						message,
						ex.getMessage());
			}
		});
	}
}
