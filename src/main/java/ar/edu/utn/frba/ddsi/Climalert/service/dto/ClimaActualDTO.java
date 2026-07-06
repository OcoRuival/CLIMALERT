package ar.edu.utn.frba.ddsi.Climalert.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaActualDTO {
  private Localizacion location;
  private TemperaturaActual current;

  public ClimaActualDTO() {
  }
//ME TIRA ERROR EL SERVICE Y NO SE VA POR ESO PONGO GETTERS, RARO
  public Localizacion getLocation() {
    return location;
  }

  public TemperaturaActual getCurrent(){
    return current;
  }
}
