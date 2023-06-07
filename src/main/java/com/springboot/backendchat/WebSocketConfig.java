package com.springboot.backendchat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //habilita broker websocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configuramos endpoint que tendra el websocket para recibir mensajes desde el cliente, ademas se agrega el cors para la app angular.
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-websocket").setAllowedOrigins("http://localhost:4200", "*").withSockJS();
    }

    /**
     * Nombre(prefijo) del evento que emitira el servidor a los clientes que estan suscritos
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //prefijo para el nombre del evento, los clientes deben suscribirse a este evento para recibir la respuesta
        registry.enableSimpleBroker("/chat");

        //prefijo para el destino del mensaje, aca los suscriptores deben enviar el mensaje
        registry.setApplicationDestinationPrefixes("/app");
    }
}
