package com.los.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class for otp model to create otp table
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "otp_table")
public class OtpModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String otpId;
	@Column(nullable = false)
	private Long countryCode;
	private String mobileNumber;
	private String processName;
	private String status;
	@Column(nullable = false)
	private String createdBy;
	@Column(nullable = false)
	private LocalDateTime createdOn;
}
