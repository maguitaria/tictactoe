package com.example.tictactoe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**!SECTION
 * Configuration for setting up WebSocket messaging in the application.
 * Enables the use of @STOMP (Simple Text Oriented Messaging Protocol) for sending
 * messages between clients
 * @author Mariia Glushenkova
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**!SECTION
     * Registers the "/ws" endpoint, allowing clients to connect to the WebSocket.
     * 
     * @param registry for registering STOMP endpoints
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
    /**!SECTION
     * Configures message broker to use destination prefixes to filter messages
     * "/app" messages are gonna be routed to message handling methods
     * "/queue", "/topic" are routed to message brokers
     * Message broker is streaming all messages to subscripbed clients
     * @param registry for configuring mesage broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/queue", "/topic", "/user");
        registry.setUserDestinationPrefix()
    }

}
