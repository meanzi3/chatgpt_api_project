package com.example.chatgpt_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
  private String role;
  private String content;


  public Message(String user, String prompt) {
    this.role = user;
    this.content = prompt;
  }
}
