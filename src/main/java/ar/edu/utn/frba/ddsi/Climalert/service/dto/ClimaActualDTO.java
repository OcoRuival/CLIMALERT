package ar.edu.utn.frba.ddsi.Climalert.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaActualDTO {
  private static Localizacion location;
  private static TemperaturaActual current;

  public ClimaActualDTO() {
  }
}
