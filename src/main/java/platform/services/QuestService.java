package platform.services;

import platform.dtos.QuestDto;
import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.constant.Platform;

import java.util.List;

public interface QuestService {

    List<QuestDto> findAll();

    List<Match> scan(Platform platform, String username);

    void create(QuestDto questDto);

    boolean select(Platform platform, String username, String questId);

    List<QuestDto> available(Platform platform, String username);
}
