package platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.services.RiotApiService;
import platform.utils.riot.constant.Platform;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/riot")
public class RiotApiController {

    private final RiotApiService riotApiService;

    @PostMapping("update")
    public void update(Platform platform, String name) {
        riotApiService.update(platform, name);
    }

}
