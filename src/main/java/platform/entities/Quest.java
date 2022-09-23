package platform.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.enums.QuestType;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document("quests")
public class Quest {

    @Id
    private Long id;

    private Integer value;

    private QuestType type;

}
