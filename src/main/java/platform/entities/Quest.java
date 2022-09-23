package platform.entities;

import lombok.*;
import platform.enums.QuestType;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quests")
public class Quest extends BaseEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    private QuestType type;

}
