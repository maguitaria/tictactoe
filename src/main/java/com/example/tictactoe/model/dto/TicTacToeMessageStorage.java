package com.example.tictactoe.model.dto;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class TicTacToeMessageStorage {
    private static final List<TicTacToeMessage> messages = new ArrayList<>();
    private static final String filePath = "messages.txt"; // Define the path to the file

    // Add a message to the list and save it to the file
    public static void appendMessage(TicTacToeMessage message) {
        messages.add(message);
        saveToFile(message); // Save the message to the file
    }

    // Save a message to the file
    private static void saveToFile(TicTacToeMessage message) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            // Append the message content to the file
            writer.println(message.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Print all saved messages to the console
    public static void printMessagesToConsole() {
        for (TicTacToeMessage message : messages) {
            System.out.println(message.getContent());
        }
    }
}
    
