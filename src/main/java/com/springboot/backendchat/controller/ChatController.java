package com.springboot.backendchat.controller;

import com.springboot.backendchat.model.documents.Mensaje;
import com.springboot.backendchat.model.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class ChatController {
    private String[] coloresUsuarios = {"red","green","blue","magenta","purple","orange"};

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")//aca enviamos la respuesta, empieza con el prefijo creado en websocketconfig 'chat'
    public Mensaje recibirMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());

        if(mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(coloresUsuarios[new Random().nextInt(coloresUsuarios.length)]);
            mensaje.setTexto("Nuevo usuario");
        }
        else {
            chatService.guardarMensaje(mensaje);
        }
        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String escribiendo(String username){
        return  username.concat(" esta escribiendo...");
    }

    /**
     * Se usa convertAndSend porque se puede personalizar el endpoint y traer el id del cliente para obtener solo su historial.
     * @param clienteId
     */
    @MessageMapping("/historial")
    public void historial(String clienteId){
        webSocket.convertAndSend("/chat/historial/"+ clienteId, chatService.obtenerUltimos10Mensajes());
    }
}
