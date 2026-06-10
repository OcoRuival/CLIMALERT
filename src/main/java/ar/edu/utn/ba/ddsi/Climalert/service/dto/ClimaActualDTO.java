package ar.edu.utn.ba.ddsi.Climalert.service.dto;

import ar.edu.utn.ba.ddsi.Climalert.enums.Direccion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ClimaActualDTO() {
  private static Localizacion location;
  private static TemperaturaActual current;

  @JsonProperty("humidity")
  private static Integer humedad;

  @JsonProperty("cloud")
  private static Integer nublado;

}
