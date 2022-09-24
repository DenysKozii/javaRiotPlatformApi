package platform.entities;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    private String id;

    private String title;

    private Integer price;

    private Integer profit;

    private Integer trigger;

    private QuestType type;

}
