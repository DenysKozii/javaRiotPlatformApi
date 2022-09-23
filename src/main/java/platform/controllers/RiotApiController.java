package platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.services.RiotApiService;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/riot")
public class RiotApiController {

    private final RiotApiService riotApiService;

    @PostMapping("key")
    public void addApiKey(String apiKey) {
        riotApiService.upsertApiKey(apiKey);
    }

    @GetMapping("summoner")
    public Summoner getSummoner(Platform platform, String username) {
        return riotApiService.getSummoner(platform, username);
    }

    @GetMapping("match/list")
    public List<String> getMatchList(Platform platform, String username) {
        return riotApiService.getMatchList(platform, username);
    }

}
