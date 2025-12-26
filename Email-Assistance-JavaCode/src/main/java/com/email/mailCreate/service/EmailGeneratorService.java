package com.email.mailCreate.service;

import com.email.mailCreate.pojo.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;


    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();

    }

    public String generateEmailReply(EmailRequest emailRequest)  {
        //Build prompt
        String promt=buildPrompt(emailRequest);
        //Create request
//        Map<String,Object> request=Map.of(
//                "contents", new Object[]{
//                        Map.of("parts",new Object[]{
//                                Map.of("text",promt)
//                        })
//
//                }
//        );
        Map<String, Object> request = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", promt)
                                )
                        )
                )
        );


        //Send request
        String response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("generativelanguage.googleapis.com")
                        .path("/v1beta/models/gemini-2.5-flash:generateContent")
                        .queryParam("key", geminiApiKey)
                        .build()
                )
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return extractResponseContent(response);


    }

    private String extractResponseContent(String response)  {

        ObjectMapper  objectMapper=new ObjectMapper();
        JsonNode root= null;
        try {
            root = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return root.path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Answer the following mail clearly and concisely:");
        if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a").append(emailRequest.getTone()).append("tone");
        }
        prompt.append("Original email:\n").append(emailRequest.getEmailContent() );
        return prompt.toString();
    }
}
