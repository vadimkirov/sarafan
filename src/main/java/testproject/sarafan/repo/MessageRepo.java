package testproject.sarafan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import testproject.sarafan.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
