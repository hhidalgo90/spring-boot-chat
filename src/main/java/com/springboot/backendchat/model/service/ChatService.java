package com.springboot.backendchat.model.service;

import com.springboot.backendchat.model.documents.Mensaje;

import java.util.List;

public interface ChatService {

    public List<Mensaje> obtenerUltimos10Mensajes();

    public Mensaje guardarMensaje(Mensaje mensaje);
}
