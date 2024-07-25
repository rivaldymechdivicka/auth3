package com.tujuhsembilan.example.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Validated
@Data
@Component
@ConfigurationProperties("application.security")
public class AuthProp {

  @NotBlank
  private String uuid;

  @Min(8)
  private Integer strength = 8;

  private String systemUsername;
  private String systemPassword;

  @Min(60)
  private long accessTokenExpiry = 3600; // 1 hour default
  private long refreshTokenExpiry = 604800; // 7 days default
  private long rememberMeTokenExpiry = 2592000; // 30 days default for remember me
}

