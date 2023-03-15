package com.gpt3Chatbot;

public record ChatGPT3ResponseUsage (int prompt_tokens,
                                     int completion_tokens,
                                     int total_tokens){
}
