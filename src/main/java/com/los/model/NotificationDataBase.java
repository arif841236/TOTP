package com.los.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class NotificationDataBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String notificationId;

	private String externalRefId;

	private String externalSystem;

	private String contactDetails;

	private String eventId;

	@Column(length = 65535, columnDefinition = "text", nullable = false)
	private String notificationMessage;

	private String contactChannel;

	private LocalDateTime sentOn;

	private String sentBy;

	private String status;
}
