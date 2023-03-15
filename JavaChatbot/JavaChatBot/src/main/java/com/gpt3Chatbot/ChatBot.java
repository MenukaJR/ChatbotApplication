package com.gpt3Chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ChatBot {

    public void VirtualAssistant(String searchString) throws IOException, InterruptedException {

        ObjectMapper objMapper = new ObjectMapper();
        ChatGPT3Request chatGPT3Request = new ChatGPT3Request("text-davinci-001",searchString,0,7);
        String input = objMapper.writeValueAsString(chatGPT3Request);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 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")
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request,HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            ChatGPT3Response chatGPT3Response = objMapper.readValue(response.body(),ChatGPT3Response.class);
            String answer = chatGPT3Response.choices()[chatGPT3Response.choices().length-1].text();
            if(!answer.isEmpty()){
                System.out.println(answer.replace("\n","").trim());
            }
        }else{
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
    }

}
