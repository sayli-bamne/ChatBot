package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
@RequestMapping("/chat")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
 @PostMapping("/message")
    public String getMessage(@RequestBody String userMessage) {
        return generateResponse(userMessage);
    }

    // Main logic to generate a response based on user input
    private String generateResponse(String userMessage) {
        userMessage = userMessage.toLowerCase().trim();
        Map<String, String> responses = getResponseMap();

        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (userMessage.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return getDefaultResponse();
    }

    // Method to define possible responses based on keywords
    private Map<String, String> getResponseMap() {
        Map<String, String> responses = new HashMap<>();
        responses.put("hello", getGreetingResponse());
        responses.put("hi", getGreetingResponse());
        responses.put("weather", "I can't check the weather right now, but I hope it's nice out!");
        responses.put("how are you", "I'm just a bot, but thanks for asking! How can I assist you?");
        responses.put("bye", "Goodbye! Have a great day!");
        responses.put("nothing", "Alright! Feel free to reach out if you have any questions.");
        return responses;
    }

    // Default response if no keywords match
    private String getDefaultResponse() {
        return "I'm not sure how to respond to that. Can you ask something else?";
    }

    // Method to get a random greeting response
    private String getGreetingResponse() {
        String[] greetings = {
            "Hello! How can I assist you today?",
            "Hi there! What can I help you with?",
            "Greetings! How may I help you?",
            "Hello! What’s on your mind?"
        };
        Random rand = new Random();
        return greetings[rand.nextInt(greetings.length)];
    }
}
