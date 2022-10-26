package platform.services;

import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;

public interface RiotApiService {

    Summoner getSummoner(Platform platform, String name);

    List<String> getMatchList(Platform platform, Summoner summoner);

    Match getMatch(Platform platform, String matchId);

    void update(Platform platform, String name);

}
