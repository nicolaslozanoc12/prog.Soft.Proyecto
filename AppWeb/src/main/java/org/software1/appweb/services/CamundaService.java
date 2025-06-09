package org.software1.appweb.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CamundaService {

    @Value("${camunda.api.url}")
    private String camundaApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String iniciarProceso(String processKey) {
        String url = camundaApiUrl + "/process-definition/key/" + processKey + "/start";

        // Enviar sin variables por ahora
        Map<String, Object> request = new HashMap<>();
        request.put("variables", new HashMap<>());

        try {
            return restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            return "Error al iniciar proceso: " + e.getMessage();
        }
    }
}
