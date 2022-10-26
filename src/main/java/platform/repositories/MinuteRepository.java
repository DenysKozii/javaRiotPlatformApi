package platform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import platform.entities.Minute;

@Repository
public interface MinuteRepository extends MongoRepository<Minute, String> {

}
