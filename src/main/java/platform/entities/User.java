package platform.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.utils.riot.constant.Platform;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("users")
public class User {

    @Id
    String id;

    String name;

    Platform platform;

    Long lastUpdate = -1L;

    List<Game> games = new ArrayList<>();

}
