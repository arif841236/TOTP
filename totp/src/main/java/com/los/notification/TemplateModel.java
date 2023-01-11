package com.los.notification;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.TypeDef;
import org.hibernate.sql.JoinType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@TypeDef(name = "json", typeClass = JoinType.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "template_id")
	private String templateId;
	@Column(name = "process_name")
	private String processName;
	@Column(name = "template_body", length = 65535, columnDefinition = "text", nullable = false)
	private String templateBody;
	@Column(name = "notification_type")
	private String notificationType;
	@Column(name = "notification_channel", columnDefinition = "json")
	@Convert(attributeName = "data", converter = JSONObjectConverter.class)
	private String[] notificationChannel;
	private Timestamp createdAt;
	@Column(name = "created_by")
	private String createdBy;
	
}
