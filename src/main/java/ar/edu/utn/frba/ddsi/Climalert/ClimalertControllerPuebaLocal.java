package ar.edu.utn.frba.ddsi.Climalert;

import ar.edu.utn.frba.ddsi.Climalert.model.Clima;
import ar.edu.utn.frba.ddsi.Climalert.repository.ClimaRepository;
import ar.edu.utn.frba.ddsi.Climalert.service.ClimalertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClimalertControllerPuebaLocal {

  private final ClimalertService service;
  private final ClimaRepository repo;

  public ClimalertControllerPuebaLocal(ClimalertService service, ClimaRepository repo) {
    this.service = service;
    this.repo = repo;
  }

  @GetMapping("/test-clima")
  public Clima forzarProcesamiento() {
    service.obtenerClimas();
    return repo.obtenerUltimoRegistro().orElse(null);
  }
}