package ar.edu.utn.frba.ddsi.Climalert.service;

import ar.edu.utn.frba.ddsi.Climalert.config.ClimalertProperties;
import ar.edu.utn.frba.ddsi.Climalert.model.ClimaEntity;
import ar.edu.utn.frba.ddsi.Climalert.repository.ClimaRepository;
import ar.edu.utn.frba.ddsi.Climalert.service.dto.ClimaActualDTO;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.LocalDateTime;

@EnableScheduling
@Component
public class ClimalertService {

  private RestTemplate restTemplate;
  private ClimalertProperties properties;
  private ClimaRepository climaRepository;
  //Usar un mailsender es poco extensible a futuro, pero en la consigna deja claro que se utiliza mail como medio
  //tampoco se nombra mucho de que a futuro puedan llegar a cambiar, ni que buscan expandirlo, por lo que no considero que sea un problema
  private final JavaMailSender mailSender;

  public ClimalertService(RestTemplate restTemplate, ClimalertProperties properties, ClimaRepository climaRepository, JavaMailSender mailSender) {
    this.restTemplate = restTemplate;
    this.properties = properties;
    this.climaRepository = climaRepository;
    this.mailSender = mailSender;
  }

  @Scheduled(fixedDelay = 300000)
  public void obtenerClimas(){
    URI uri = UriComponentsBuilder
        .fromUriString(properties.getUrl())
        .path("/current.json")
        .queryParam("key", properties.getKey())
        .queryParam("q", "CABA")
        .build()
        .toUri();


      ClimaActualDTO respuesta = restTemplate.getForObject(uri, ClimaActualDTO.class);

      if (respuesta != null) {
        ClimaEntity entidad = new ClimaEntity();
        entidad.setFechaRegistro(LocalDateTime.now());
        entidad.setLocalidad(respuesta.getLocation().getName());
        entidad.setTemperatura(respuesta.getCurrent().getTemp_c());
        entidad.setHumedad(respuesta.getCurrent().getHumidity());

        climaRepository.save(entidad);
      }

  }

  @Scheduled(fixedDelay = 60000)
  public void procesar(){
    climaRepository.obtenerUltimoRegistro().ifPresent(ultimoClima -> {

      boolean temperaturaCritica = ultimoClima.getTemperatura() > 35.0;
      boolean humedadCritica = ultimoClima.getHumedad() > 60;

      if (temperaturaCritica && humedadCritica) {
        notificar(ultimoClima);
      }
    });
  }


  public void notificar(ClimaEntity clima){
    String[] destinatarios = {"admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com"};

    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(destinatarios);
    mensaje.setSubject("Clima critico" + clima.getLocalidad());
    mensaje.setText(String.format(
        "Se han detectado condiciones climaticas peligrosas.\n\n" +
            "Detalles actuales:\n" +
            "- Localidad: %s\n" +
            "- Temperatura: %.1f°C\n" +
            "- Humedad: %d%%\n" +
            "- Fecha de registro: %s",
        clima.getLocalidad(), clima.getTemperatura(), clima.getHumedad(), clima.getFechaRegistro()
    ));

    try {
      mailSender.send(mensaje);
      System.out.println("Correos de alerta enviados correctamente.");
    } catch (Exception e) {
      System.err.println("Error enviando correos (verifica la configuracion de SMTP). " + e.getMessage());
    }
  }
  }

