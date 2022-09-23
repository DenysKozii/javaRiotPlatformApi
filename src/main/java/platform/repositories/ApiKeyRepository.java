package platform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import platform.entities.ApiKey;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKey, Long> {
}
