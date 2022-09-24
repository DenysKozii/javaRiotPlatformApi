package platform.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import platform.enums.QuestType;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuestDto {

    private String id;

    @NonNull
    private String title;

    @NonNull
    private Integer price;

    @NonNull
    private Integer profit;

    @NonNull
    private Integer trigger;

    @NonNull
    private QuestType type;

}
