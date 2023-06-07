package com.springboot.backendchat.model.service;

import com.springboot.backendchat.model.dao.ChatDao;
import com.springboot.backendchat.model.documents.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatDao chatDao;
    @Override
    public List<Mensaje> obtenerUltimos10Mensajes() {
        return chatDao.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardarMensaje(Mensaje mensaje) {
        return chatDao.save(mensaje);
    }
}
