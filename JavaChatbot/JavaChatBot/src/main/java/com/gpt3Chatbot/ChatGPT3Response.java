package com.gpt3Chatbot;

public record ChatGPT3Response(
        String id,
        String object,
        int created,
        String model,
        ChatGPT3ResponseChoice[] choices,
        ChatGPT3ResponseUsage usage
)
{

}
