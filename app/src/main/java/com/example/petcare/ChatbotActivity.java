package com.example.petcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;
import com.example.petcare.R;


public class ChatbotActivity extends AppCompatActivity {

    private EditText userInputEditText;
    private TextView chatHistoryTextView;
    private Button sendButton;
    // Define predefined responses
    private Map<String, String> predefinedResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        userInputEditText = findViewById(R.id.userInput);
        chatHistoryTextView = findViewById(R.id.chatHistoryTextView);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        // Initialize predefined responses
        predefinedResponses = new HashMap<>();
        predefinedResponses.put("hello", "Hello, how are you?");
        predefinedResponses.put("hi", "Hi there, how can I assist you?");
        predefinedResponses.put("good morning", "Good morning! What can I do for you today?");
        predefinedResponses.put("welcome", "Hello! Welcome to Furr Budy.");
        predefinedResponses.put("good morning", "Good morning! Welcome to Furr Budy.");
        predefinedResponses.put("good afternoon", "Good afternoon! Welcome to Furr Budy.");
        predefinedResponses.put("good evening", "Good evening! Welcome to Furr Budy.");

    }

    private void sendMessage() {
        String userInput = userInputEditText.getText().toString().trim();
        if (!userInput.isEmpty()) {
            // Display user's message in chat history
            appendToChatHistory("You: " + userInput);

            // Get response based on user input
            String botResponse = getBotResponse(userInput);

            // Display bot's response in chat history
            appendToChatHistory("FurrBuddy: " + botResponse);

            // Clear input field
            userInputEditText.setText("");

            // Scroll to bottom of chat history
            scrollToBottom();
        }
    }

    private boolean isAskingCalmQuestion = false;

    private String getBotResponse(String userInput) {
        // Check if the user input has a predefined response
        if (predefinedResponses.containsKey(userInput.toLowerCase())) {
            // Return the predefined response
            return predefinedResponses.get(userInput.toLowerCase());
        } else {
            // Check for specific keywords in the user input
            if (userInput.toLowerCase().contains("pet")) {
                // If the user input contains "pet", ask about the pet
                return "What pet do you have?";
            } else if (userInput.toLowerCase().contains("dog") ||
                    userInput.toLowerCase().contains("cat") ||
                    userInput.toLowerCase().contains("rabbit") ||
                    userInput.toLowerCase().contains("hamster")) {
                // If the user input contains one of these pets, ask about the pet's calmness
                isAskingCalmQuestion = true;
                return "Is your pet calm right now?";
            } else if (userInput.toLowerCase().contains("fish")) {
                // If the user input contains "fish", ask about the feeding frequency
                return "How many times do you feed the fish?";
            } else if (isAskingCalmQuestion) {
                // Check if the question about the pet's calmness was asked
                if (userInput.toLowerCase().contains("no")) {
                    // If the user input contains "no", suggest playing white noise
                    isAskingCalmQuestion = false;
                    return "You can play some white noise from YouTube.";
                } else if (userInput.toLowerCase().contains("yes")) {
                    // If the user input contains "yes", reward the pet
                    isAskingCalmQuestion = false;
                    return "Yay! That furry friend deserves a treat.";
                }
            } else if (userInput.matches("\\d+")) {
                // Check if the user input is a number (feeding frequency)
                int frequency = Integer.parseInt(userInput);
                if (frequency > 1) {
                    return "You should only feed the fish once in a day, that too in a limited quantity.";
                } else if (frequency == 1) {
                    return "That's really great, you don't overfeed your fish.";
                } else {
                    return "Invalid input. Please enter a positive number.";
                }
            } else if (userInput.toLowerCase().equals("okay") ||
                    userInput.toLowerCase().equals("ok") ||
                    userInput.toLowerCase().equals("alright")) {
                // If the user input is one of these phrases, offer further assistance
                return "Do you need any more assistance?";
            } else if (userInput.toLowerCase().equals("no")) {
                // If the user input is "no" after asking for further assistance, don't reply
                return "";
            }
            // If no predefined response or specific keyword found, return a default response
            return "I'm sorry, I didn't understand that.";
        }
    }





    private void appendToChatHistory(String message) {
        chatHistoryTextView.append(message + "\n");
    }

    private void scrollToBottom() {
        final ScrollView scrollView = findViewById(R.id.chatScrollView);
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
