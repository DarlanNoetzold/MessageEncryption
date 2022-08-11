package tech.noetzold.MessageEncryption.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.noetzold.MessageEncryption.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
