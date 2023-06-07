package com.springboot.backendchat.model.dao;

import com.springboot.backendchat.model.documents.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatDao extends MongoRepository<Mensaje, String> {

    public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
