package ar.edu.utn.frba.ddsi.Climalert.repository;

import ar.edu.utn.frba.ddsi.Climalert.model.Clima;
import java.util.Optional;

public interface ClimaRepository{

  Clima guardar(Clima clima);
  Optional<Clima> obtenerUltimoRegistro();
}