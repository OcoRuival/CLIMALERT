package ar.edu.utn.frba.ddsi.Climalert.model;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class Clima {
  private Long id;

  private LocalDateTime fechaRegistro;
  private String localidad;
  private Double temperatura;
  private Integer humedad;
}