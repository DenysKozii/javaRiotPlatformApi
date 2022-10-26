package platform.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.enums.QuestType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document("games")
public class Game {

    @Id
    String id;

    String gameMode;

    String gameType;

    String championName;

    Integer kills;

    Integer deaths;

    Integer assists;

    List<Minute> minutes = new ArrayList<>();

}
