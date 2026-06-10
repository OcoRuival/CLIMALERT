package ar.edu.utn.ba.ddsi.Climalert.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@Component
public class ClimalertService {

  private RestTemplate restTemplate;

  @Scheduled(fixedDelay = 300000)
  public void obtenerClimas(){}

  @Scheduled(fixedDelay = 60000)
  public void procesar(){}


  public void notificar(){}
}
