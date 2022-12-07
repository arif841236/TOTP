package com.indusnet.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import com.indusnet.model.common.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OtpData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_seq")
	@SequenceGenerator(name = "otp_seq", sequenceName = "otp_sequence", initialValue = 2001)
	@Column(length = 20)
	private BigInteger id;
	private Type type;
	@Column(unique = true,nullable = false)
	private String typeValue;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "logged_in_user")
	private UserModel user;
	private LocalDateTime otpGeneratedAt;
	@Column(name = "notification_id")
	private Integer messageId;
	private LocalDateTime validatedAt;
	private LocalDateTime resendInitiatedAt;
	private String requestdevice;
	private LocalDateTime requestedAt;
	private LocalDateTime validupto;

}
