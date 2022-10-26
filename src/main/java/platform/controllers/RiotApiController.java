package platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.services.RiotApiService;
import platform.utils.riot.constant.Platform;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/riot")
public class RiotApiController {

    private final RiotApiService riotApiService;

    @GetMapping("update")
    public void update(Platform platform, String summonerName) {
        riotApiService.update(platform, summonerName);
    }

}
