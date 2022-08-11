package tech.noetzold.MessageEncryption.repository;

import tech.noetzold.MessageEncryption.model.Message;

import java.util.List;

public interface ServiceImpl {

    List<Message> getOneById(Long id);
    List<Message> getAll();
    Message getOne(Long id);
    void save(Message message);
}
