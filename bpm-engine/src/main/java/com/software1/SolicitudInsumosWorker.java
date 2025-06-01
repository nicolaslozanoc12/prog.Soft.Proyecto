package com.software1;


import com.software1.models.SolicitudInsumos;
import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SolicitudInsumosWorker {
//
//    private ExternalTaskClient client;
//
//    @PostConstruct
//    public void initializeWorker() {
//        // Crear el cliente para conectarse al motor de Camunda
//        client = ExternalTaskClient.create()
//                .baseUrl("http://localhost:9000/engine-rest") // URL del motor de Camunda
//                .asyncResponseTimeout(10000) // Tiempo de espera para respuestas
//                .build();
//
//        // Suscribirse al topic "enviarSolicitudInsumos"
//        client.subscribe("enviarSolicitudInsumos")
//                .lockDuration(1000)
//                .handler((externalTask, externalTaskService) -> {
//                    // Obtener datos del proceso
//                    String nombreProveedor = externalTask.getVariable("noProveedor");
//                    Long insumo = externalTask.getVariable("insumo");
//                    Integer cantidad = externalTask.getVariable("cantidad");
//
//                    // Crear el cuerpo de la solicitud
//                    SolicitudInsumos solicitud = new SolicitudInsumos(nombreProveedor, insumo, cantidad);
//
//                    // Realizar la llamada HTTP
//                    RestTemplate restTemplate = new RestTemplate();
//                    String url = "http://localhost:9000/api/solicitudes"; // Endpoint de la API
//                    ResponseEntity<String> response = restTemplate.postForEntity(url, solicitud, String.class);
//
//                    // Procesar la respuesta
//                    System.out.println("Respuesta de la API: " + response.getBody());
//
//                    // Devolver las cantidades al motor de Camunda
//                    externalTaskService.complete(externalTask, Map.of(
//                            "cantidadSolicitada", cantidad,
//                            "id", insumo,
//                            "respuestaAPI", response.getBody()
//                    ));
//                })
//                .open();
//    }
}
