package platform.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import platform.dtos.QuestDto;
import platform.entities.Quest;

@Mapper(componentModel = "spring")
public interface QuestMapper {
    QuestMapper INSTANCE = Mappers.getMapper(QuestMapper.class);

    QuestDto mapToDto(Quest quest);
}
