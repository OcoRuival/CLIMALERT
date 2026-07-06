package ar.edu.utn.frba.ddsi.Climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "climalert")
@Data
public class ClimalertProperties {

  private String url;
  private String key;
}
