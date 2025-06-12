package org.software1.web_app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CamundaService {
    @Value("${camunda.api.url}")
    private String camundaApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Inicia una instancia de proceso con variables.
     */
    public String iniciarProceso(String processKey, Map<String, Object> variables) {
        String url = camundaApiUrl + "engine-rest/process-definition/key/" + processKey + "/start";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("variables", variables);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Hacemos la llamada POST a la API de Camunda
            return restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al iniciar proceso: " + e.getMessage();
        }
    }

    /**
     * Obtiene las tareas asignadas a un grupo candidato.
     */
    public List<Map<String, Object>> getTasksForGroup(String group) {
        String url = camundaApiUrl + "engine-rest/task?candidateGroup=" + group;
        try {
            // Hacemos la llamada GET a la API de Camunda
            return restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Completa una tarea específica, enviando variables.
     */
    public void completeTask(String taskId, Map<String, Object> variables) {
        String url = camundaApiUrl + "engine-rest/task/" + taskId + "/complete";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("variables", variables);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Hacemos la llamada POST a la API de Camunda
            restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Obtiene los detalles completos de una sola tarea.
     */
    public Map<String, Object> getTaskDetails(String taskId) {
        String url = camundaApiUrl + "engine-rest/task/" + taskId;
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
    public Map<String, Object> getTaskVariables(String taskId) {
        String url = camundaApiUrl + "engine-rest/task/" + taskId + "/variables";
        try {
            // Hacemos la llamada GET a la API de Camunda
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
}
