package platform.services.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.entities.User;
import platform.repositories.UserRepository;
import platform.services.RiotApiService;
import platform.utils.riot.api.ApiConfig;
import platform.utils.riot.api.RiotApi;
import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;

@AllArgsConstructor
@Service
public class RiotApiServiceImpl implements RiotApiService {

    @Value("${riot.api.key}")
    private final String key = "";
    private final ApiConfig config = new ApiConfig().setKey(key);
    private final RiotApi api = new RiotApi(config);
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public Summoner getSummoner(Platform platform, String summonerName) {
        return api.getSummonerByName(platform, summonerName);
    }

    @SneakyThrows
    @Override
    public List<String> getMatchList(Platform platform, String summonerName) {
        User user = userRepository.findByPlatformAndName(platform, summonerName)
                .orElseThrow(RuntimeException::new);
        return api.getMatchListByAccountId(platform.convert(), getSummoner(platform, summonerName).getPuuid(), user.getLastUpdate());
    }

    @SneakyThrows
    @Override
    public Match getMatch(Platform platform, String matchId) {
        return api.getMatch(platform, matchId);
    }

    @Override
    public void update(Platform platform, String summonerName) {
        System.out.println("key : " + key);
        List<String> matches = getMatchList(platform, summonerName);
        System.out.println(matches);
        // save new matches
    }

}
