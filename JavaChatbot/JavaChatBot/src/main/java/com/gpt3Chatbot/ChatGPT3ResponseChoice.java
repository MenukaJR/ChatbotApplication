package com.gpt3Chatbot;

public record ChatGPT3ResponseChoice(
        String text,
        int index,
        Object logprobs,
        String finish_reason
) {
}
