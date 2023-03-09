package com.los.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * This is kafka configuration class
 * its config the serialize and deserialize and also create topic.
 * @author Md Arif
 *
 */
@Configuration
public class KafkaConfig {

	@Bean
	ProducerFactory<String, Object> producerFactory() {

		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "35.154.35.53:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);	

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	NewTopic createTopic() {
		return TopicBuilder.name("totp")
				.build();
	}

	@Bean
	ConsumerFactory<String, KafkaResponse> consumerFactory() {

		Map<String, Object> config = new HashMap<>();
		JsonDeserializer<KafkaResponse> deserializer = new JsonDeserializer<>(KafkaResponse.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "35.154.35.53:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),
				deserializer);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, KafkaResponse> concurrentKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, KafkaResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

}
