package platform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import platform.entities.User;
import platform.utils.riot.constant.Platform;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByPlatformAndName(Platform platform, String name);

}
