package platform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.entities.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
}
