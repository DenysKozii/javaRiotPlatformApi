package platform.services;

import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;

public interface RiotApiService {

    Summoner getSummoner(Platform platform, String summonerName);

    List<String> getMatchList(Platform platform, String summonerName);

    Match getMatch(Platform platform, String matchId);

    void update(Platform platform, String summonerName);

}
