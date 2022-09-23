package platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.dtos.QuestDto;
import platform.services.QuestService;

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

    @GetMapping("scan")
    public List<QuestDto> scan(String username) {
        return questService.scan(username);
    }

}
