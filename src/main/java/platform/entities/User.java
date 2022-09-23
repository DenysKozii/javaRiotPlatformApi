package platform.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {

    @Id
    private Long id;

    private String name;

    private String password;

    private Integer points = 0;

}
