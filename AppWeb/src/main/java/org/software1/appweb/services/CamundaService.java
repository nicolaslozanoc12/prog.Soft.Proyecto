package org.software1.appweb.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class CamundaService {

    private final RestTemplate restTemplate;

    @Value("${camunda.api.url}") // Ej: http://localhost:9000/engine-rest
    private String camundaApiUrl;


    public CamundaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Iniciar proceso
    public String iniciarProceso(String keyProceso) {
        String url = camundaApiUrl + "/process-definition/key/" + keyProceso + "/start";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{}"; // Sin variables

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }

    public List<Map<String, Object>> getTasks() {
        String url = camundaApiUrl + "/task";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        return response.getBody();
    }

    // Obtener tareas por usuario
    public String obtenerTareas(String usuario) {
        String url = camundaApiUrl + "/task?assignee=" + usuario;
        return restTemplate.getForObject(url, String.class);
    }

    // Reclamar tarea
    public void reclamarTarea(String taskId, String userId) {
        String url = camundaApiUrl + "/task/" + taskId + "/claim";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = """
        {
            "userId": "%s"
        }
        """.formatted(userId);

        restTemplate.postForEntity(url, new HttpEntity<>(body, headers), String.class);
    }
    public Map<String, Object> getTaskInfo(String taskId) {
        String url = camundaApiUrl + "/task/" + taskId;
        return restTemplate.getForObject(url, Map.class);
    }

    // Completar tarea
    public void completarTarea(String taskId, Map<String, Object> formData) {
        String url = camundaApiUrl + "/task/" + taskId + "/complete";
        Map<String, Object> request = new HashMap<>();
        Map<String, Object> variables = new HashMap<>();
        formData.forEach((k, v) -> {
            Map<String, Object> var = new HashMap<>();
            var.put("value", v);
            variables.put(k, var);
        });
        request.put("variables", variables);
        restTemplate.postForEntity(url, request, String.class);
    }

    // 🔧 Método utilitario para formatear variables a JSON estilo Camunda
    private String toCamundaJsonVariables(Map<String, Object> variables) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        variables.forEach((key, value) -> {
            sb.append("\"").append(key).append("\": { \"value\": \"").append(value).append("\" },");
        });
        if (!variables.isEmpty()) sb.setLength(sb.length() - 1); // quitar coma final
        sb.append("}");
        return sb.toString();
    }
    public Map getFormVariables(String taskId) {
        String url = camundaApiUrl + "/task/" + taskId + "/form-variables";
        return restTemplate.getForObject(url, Map.class);
    }
}
