package platform.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.services.RiotApiService;
import platform.utils.riot.constant.Platform;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class RiotApiController {

    private final RiotApiService riotApiService;

    @PostMapping
    public void search(Platform platform, String name) {
        riotApiService.search(platform, name);
    }

}
