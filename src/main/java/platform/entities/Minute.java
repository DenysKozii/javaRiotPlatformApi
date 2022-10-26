package platform.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.utils.riot.api.endpoints.match.dto.MatchPosition;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("minutes")
public class Minute {

    @Id
    String id;

    Integer minute;

    Integer minionsKilled;

    Integer jungleMinionsKilled;

    Integer totalGold;

    Integer currentGold;

    Integer teamScore;

    Integer level;

    Integer xp;

    MatchPosition position;

}
