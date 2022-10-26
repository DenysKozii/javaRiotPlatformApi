package platform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import platform.entities.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

}
