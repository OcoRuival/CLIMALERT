package ar.edu.utn.frba.ddsi.Climalert.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condicion {
  private String text;
  private String icon;
  private Integer code;
}
