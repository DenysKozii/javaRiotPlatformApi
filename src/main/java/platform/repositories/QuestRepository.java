package platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.entities.ApiKey;
import platform.entities.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
