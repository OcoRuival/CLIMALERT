package ar.edu.utn.frba.ddsi.Climalert.repository;

import ar.edu.utn.frba.ddsi.Climalert.model.Clima;
import ar.edu.utn.frba.ddsi.Climalert.utils.GeneradorIdSecuencial;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClimaRepository implements ClimaRepository{

  private final GeneradorIdSecuencial generador = new GeneradorIdSecuencial();
  private final List<Clima> climas = new ArrayList<>();


  @Override
  public Clima guardar(Clima clima) {
    if (clima.getId() == null) {
      clima.setId(generador.siguiente());
    }
      climas.add(clima);
    return clima;
  }

  @Override
  public Optional<Clima> obtenerUltimoRegistro() {
    if (climas.isEmpty()) {
      return Optional.empty();
    }

    return climas.stream()
        .max(Comparator.comparing(Clima::getFechaRegistro));
  }
  }

