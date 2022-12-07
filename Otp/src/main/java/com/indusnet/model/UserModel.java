package com.indusnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@ToString
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "register_seq")
	@SequenceGenerator(name = "register_seq", sequenceName = "regis_seq", initialValue = 1001)
	private Integer userId;
	private Integer loggedIn;
    
}
