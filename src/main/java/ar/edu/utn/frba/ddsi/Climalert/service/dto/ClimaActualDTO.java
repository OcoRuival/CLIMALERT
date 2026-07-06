package ar.edu.utn.frba.ddsi.Climalert.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaActualDTO {
  private static Localizacion location;
  private static TemperaturaActual current;

  public ClimaActualDTO() {
  }
//ME TIRA ERROR EL SERVICE Y NO SE VA POR ESO PONGO GETTERS, RARO
  public static Localizacion getLocation() {
    return location;
  }

  public static TemperaturaActual getCurrent(){
    return current;
  }
}
