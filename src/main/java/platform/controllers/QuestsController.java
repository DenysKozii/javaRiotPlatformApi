package platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import platform.dtos.QuestDto;
import platform.services.QuestService;
import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.constant.Platform;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/quests")
public class QuestsController {

    private final QuestService questService;

    @GetMapping
    public List<QuestDto> findAll() {
        return questService.findAll();
    }

    @PostMapping
    public void create(QuestDto questDto) {
        questService.create(questDto);
    }

    @GetMapping("scan")
    public List<Match> scan(Platform platform, String username) {
        return questService.scan(platform, username);
    }

    @GetMapping("available")
    public List<QuestDto> getAvailable(Platform platform, String username) {
        return questService.available(platform, username);
    }

    @PatchMapping("select")
    public boolean select(Platform platform, String username, String questId) {
        return questService.select(platform, username, questId);
    }

}
