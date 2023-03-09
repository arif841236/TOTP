package com.los.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// This class for showing validation response;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ValidationResponse {
	private Integer status;
	private String message;
}
