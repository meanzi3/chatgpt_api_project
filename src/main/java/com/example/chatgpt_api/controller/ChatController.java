package com.example.chatgpt_api.controller;

import com.example.chatgpt_api.dto.ChatRequest;
import com.example.chatgpt_api.dto.ChatResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class ChatController {

  @Qualifier("openaiRestTemplate")
  private final RestTemplate restTemplate;

  @Value("${openai.model}")
  private String model;

  @Value("${openai.api.url}")
  private String apiUrl;

  @GetMapping("/chat")
  public String chat(@RequestParam String prompt) {
    // create a request
    ChatRequest request = new ChatRequest(model, prompt);

    // call the API
    ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

    if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
      return "No response";
    }

    // return the first response
    return response.getChoices().get(0).getMessage().getContent();
  }
}
