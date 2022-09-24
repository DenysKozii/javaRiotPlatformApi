package platform.services;

import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;

public interface RiotApiService {

    void upsertApiKey(String apiKey);

    String getApiKey();

    Summoner getSummoner(Platform platform, String username);

    List<String> getMatchList(Platform platform, String username);

    Match getMatch(Platform platform, String matchId);

}
