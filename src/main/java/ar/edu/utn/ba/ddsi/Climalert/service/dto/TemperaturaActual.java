package ar.edu.utn.ba.ddsi.Climalert.service.dto;

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
  private LocalTime last_updated;
  private Double temp_c;
  private boolean is_Day;
  private Condicion condition;

}
