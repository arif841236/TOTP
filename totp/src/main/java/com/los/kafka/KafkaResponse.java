package com.los.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KafkaResponse {
    
    private String otpId;
    private String  countryCode;
    private String mobile;
    private String otp;
    private String generatedTime;
    private String processName;
    

}
