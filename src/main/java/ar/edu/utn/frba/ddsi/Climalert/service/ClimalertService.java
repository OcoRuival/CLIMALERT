package ar.edu.utn.frba.ddsi.Climalert.service;

import ar.edu.utn.frba.ddsi.Climalert.config.ClimalertProperties;
import ar.edu.utn.frba.ddsi.Climalert.repository.ClimaRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@EnableScheduling
@Component
public class ClimalertService {

  private RestTemplate restTemplate;
  private ClimalertProperties properties;
  private final ClimaRepository climaRepository;
  private final JavaMailSender mailSender;

  public ClimalertService(RestTemplate restTemplate, ClimalertProperties properties) {
    this.restTemplate = restTemplate;
    this.properties = properties;
  }

  @Scheduled(fixedDelay = 300000)
  public void obtenerClimas(){
    URI uri = UriComponentsBuilder
        .fromUriString(properties.getUrl())
        .path("/current.json")
        //ACA HAY ALGO DE LOS PARAMS
        //TENGO QUE AGREGARLE LO DE PASARLE LA KEY
        .build()
        .toUri();
  }

  @Scheduled(fixedDelay = 60000)
  public void procesar(){}


  public void notificar(){}
}
