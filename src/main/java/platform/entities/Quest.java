package platform.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import platform.enums.QuestType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document("quests")
public class Quest extends BaseEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    private QuestType type;

}
