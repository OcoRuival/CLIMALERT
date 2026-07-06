package ar.edu.utn.frba.ddsi.Climalert.repository;

import ar.edu.utn.frba.ddsi.Climalert.model.ClimaEntity;
import java.util.Optional;

public interface ClimaRepository{

  ClimaEntity save(ClimaEntity clima);
  Optional<ClimaEntity> obtenerUltimoRegistro();
}