package com.gpt3Chatbot;

public record ChatGPT3Request(String model, String prompt, int temperature, int max_tokens) {

}
