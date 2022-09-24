package platform.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {

    @Id
    private String id;

    private String name;

    private String password;

    private Integer points = 0;

    @DBRef(lazy = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Quest> availableQuests = new ArrayList<>();

    @DBRef(lazy = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Quest> passedQuests = new ArrayList<>();

}
