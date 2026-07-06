package ar.edu.utn.frba.ddsi.Climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "climalert")
@Configuration
@Data
public class ClimalertProperties {


  private String url;
  private String key;
}
