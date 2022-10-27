package platform.services.impl;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.entities.Game;
import platform.entities.Minute;
import platform.entities.User;
import platform.repositories.UserRepository;
import platform.services.RiotApiService;
import platform.utils.riot.api.ApiConfig;
import platform.utils.riot.api.RiotApi;
import platform.utils.riot.api.endpoints.match.dto.*;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RiotApiServiceImpl implements RiotApiService {

    String riotApiKey;
    ApiConfig config;
    RiotApi api;
    UserRepository userRepository;

    @Autowired
    public RiotApiServiceImpl(@Value("${riot.api.key}") String riotApiKey,
                              ApiConfig config,
                              RiotApi api,
                              UserRepository userRepository) {
        this.config = config;
        this.api = api;
        this.userRepository = userRepository;
        this.riotApiKey = riotApiKey;
    }

    // TODO skip api request if puuid is known
    @SneakyThrows
    @Override
    public Summoner getSummoner(Platform platform, String name) {
        config.setKey(riotApiKey);
        return api.getSummonerByName(platform, name);
    }

    @SneakyThrows
    @Override
    public List<String> getMatchList(Platform platform, Summoner summoner) {
        return api.getMatchListByAccountId(platform.convert(), summoner.getPuuid(), -1);
    }

    @SneakyThrows
    @Override
    public Info getMatchInfo(Platform platform, String matchId) {
        return api.getMatch(platform, matchId).getInfo();
    }

    // TODO read puuid
    private User updateUser(Platform platform, String name) {
        userRepository.findByPlatformAndName(platform, name).ifPresent(userRepository::delete);
        return User.builder()
                .name(name)
                .platform(platform)
                .lastUpdate(new Timestamp(System.currentTimeMillis()).getTime())
                .build();
    }

    @SneakyThrows
    @Override
    public void search(Platform platform, String name) {
        User user = updateUser(platform, name);
        Summoner summoner = getSummoner(platform, name);
        log.info("Update information for {}", summoner);
        List<String> matches = getMatchList(platform, summoner);
        for (String id : matches) {
            Info info = getMatchInfo(platform.convert(), id);
            List<Participant> participants = info.getParticipants();
            for (Participant participant : participants) {
                if (participant.getPuuid().equals(summoner.getPuuid())) {
                    List<Minute> minutes = getTimeline(platform, id, summoner.getPuuid());
                    Game game = Game.builder()
                            .gameType(info.getGameType())
                            .gameMode(info.getGameMode())
                            .championName(participant.getChampionName())
                            .kills(participant.getKills())
                            .deaths(participant.getDeaths())
                            .assists(participant.getAssists())
                            .gameMode(info.getGameMode())
                            .minutes(minutes)
                            .build();

                    user.getGames().add(game);
                    user.setLastUpdate(new Timestamp(System.currentTimeMillis()).getTime());
                }
            }
        }
        userRepository.save(user);
    }

    @SneakyThrows
    public List<Minute> getTimeline(Platform platform, String matchId, String puuid) {
        List<Minute> minutes = new ArrayList<>();
        platform = platform.convert();
        MatchTimelineInfo info = api.getTimelineByMatchId(platform, matchId).getInfo();
        int id = 1;
        for (ParticipantIdentityInfo participantIdentityInfo : info.getParticipants()) {
            id = participantIdentityInfo.getPuuid().equals(puuid) ? participantIdentityInfo.getParticipantId() : id;
        }
        int timer = 0;
        for (MatchFrame matchFrame : info.getFrames()) {
            timer++;
            MatchParticipantFrame participant = matchFrame.getParticipantFrames().get(id);
            Minute minute = Minute.builder()
                    .minute(timer)
                    .totalGold(participant.getTotalGold())
                    .currentGold(participant.getCurrentGold())
                    .minionsKilled(participant.getMinionsKilled())
                    .jungleMinionsKilled(participant.getJungleMinionsKilled())
                    .position(participant.getPosition())
                    .teamScore(participant.getTeamScore())
                    .level(participant.getLevel())
                    .xp(participant.getXp())
                    .build();
            minutes.add(minute);
        }
        return minutes;
    }

}
