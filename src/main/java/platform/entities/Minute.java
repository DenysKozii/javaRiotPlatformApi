package platform.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.utils.riot.api.endpoints.match.dto.MatchPosition;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("minutes")
public class Minute {

    @Id
    String id;

    Integer time;

    Integer minionsKilled;

    Integer jungleMinionsKilled;

    Integer totalGold;

    Integer currentGold;

    MatchPosition position;

}
