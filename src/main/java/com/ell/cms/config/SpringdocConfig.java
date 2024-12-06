package com.ell.cms.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "jwt", scheme = "bearer", in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
public class SpringdocConfig {
}
