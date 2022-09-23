package platform.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document("api_keys")
public class ApiKey {

    @Id
    private Long id;

    @NonNull
    private String value;

}
