package ar.edu.utn.frba.ddsi.Climalert.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperaturaActual {
  private Double temp_c;
  private Integer humidity;
  private Integer cloud;
  private Condicion condition;
  private String last_updated;

}
