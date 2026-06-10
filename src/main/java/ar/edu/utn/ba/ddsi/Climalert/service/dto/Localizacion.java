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
public class Localizacion {
  private String name;
  private String region;
  private String country;
  private LocalTime localTime;

}

