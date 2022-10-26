package platform.services;

import platform.entities.Minute;
import platform.utils.riot.api.endpoints.match.dto.Info;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;

public interface RiotApiService {

    Summoner getSummoner(Platform platform, String name);

    List<String> getMatchList(Platform platform, Summoner summoner);

    Info getMatchInfo(Platform platform, String matchId);

    void search(Platform platform, String name);

    List<Minute> getTimeline(Platform platform, String matchId, String puuid);

}
