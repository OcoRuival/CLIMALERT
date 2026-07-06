package ar.edu.utn.frba.ddsi.Climalert.service;

import ar.edu.utn.frba.ddsi.Climalert.config.ClimalertProperties;
import ar.edu.utn.frba.ddsi.Climalert.model.Clima;
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

  private RestTemplate template;
  private ClimalertProperties properties;
  private ClimaRepository repositorio;
  //Usar un mailsender es poco extensible a futuro, pero en la consigna deja claro que se utiliza mail como medio
  //tampoco se nombra mucho de que a futuro puedan llegar a cambiar, ni que buscan expandirlo, por lo que no considero que sea un problema
  private final JavaMailSender enviadorDeMails;

  public ClimalertService(RestTemplate template, ClimalertProperties properties, ClimaRepository repositorio, JavaMailSender enviadorDeMails) {
    this.template = template;
    this.properties = properties;
    this.repositorio = repositorio;
    this.enviadorDeMails = enviadorDeMails;
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


      ClimaActualDTO respuesta = template.getForObject(uri, ClimaActualDTO.class);

      if (respuesta != null) {
        Clima entidad = new Clima();
        entidad.setFechaRegistro(LocalDateTime.now());
        entidad.setLocalidad(respuesta.getLocation().getName());
        entidad.setTemperatura(respuesta.getCurrent().getTemp_c());
        entidad.setHumedad(respuesta.getCurrent().getHumidity());

        repositorio.guardar(entidad);
      }

  }

  @Scheduled(fixedDelay = 60000)
  public void procesar(){
    repositorio.obtenerUltimoRegistro().ifPresent(ultimoClima -> {

      boolean temperaturaCritica = ultimoClima.getTemperatura() > 35.0;
      boolean humedadCritica = ultimoClima.getHumedad() > 60;

      if (temperaturaCritica && humedadCritica) {
        notificar(ultimoClima);
      }
    });
  }


  public void notificar(Clima clima){
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
      enviadorDeMails.send(mensaje);


  }
  }

