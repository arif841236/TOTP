package com.los.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
	
	public ApiInfo getInfo() {
		
		return 
				new ApiInfo("TOTP Api Documentation", "Api Documentation", "1.0"
				, "Terms of Service"
				, null
				, "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}

