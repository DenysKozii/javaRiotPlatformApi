package platform.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.entities.Game;
import platform.entities.Minute;
import platform.entities.User;
import platform.repositories.UserRepository;
import platform.services.RiotApiService;
import platform.utils.riot.api.ApiConfig;
import platform.utils.riot.api.RiotApi;
import platform.utils.riot.api.RiotApiException;
import platform.utils.riot.api.endpoints.match.dto.*;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RiotApiServiceImpl implements RiotApiService {

    @Value("${riot.api.key}")
    private final String key = "RGAPI-d6756b99-0aa4-4f26-b4a2-f493276acb86";
    private final ApiConfig config;
    private final RiotApi api;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public Summoner getSummoner(Platform platform, String name) {
        config.setKey(key);
        return api.getSummonerByName(platform, name);
    }

    @SneakyThrows
    @Override
    public List<String> getMatchList(Platform platform, Summoner summoner) {
        return api.getMatchListByAccountId(platform.convert(), summoner.getPuuid(), -1);
    }

    @SneakyThrows
    @Override
    public Match getMatch(Platform platform, String matchId) {
        return api.getMatch(platform, matchId);
    }

    private User getOrCreateUser(Platform platform, String name) {
        return userRepository.findByPlatformAndName(platform, name)
                .orElseGet(() -> {
                    User user = new User();
                    user.setPlatform(platform);
                    user.setName(name);
                    userRepository.save(user);
                    return user;
                });
    }

    @SneakyThrows
    @Override
    public void update(Platform platform, String name) {
        User user = getOrCreateUser(platform, name);
        Summoner summoner = getSummoner(platform, name);
        List<String> matches = getMatchList(platform, summoner);
        System.out.println(matches);
        for (String id : matches) {
            Info info = api.getMatch(platform.convert(), id).getInfo();
            List<Participant> participants = info.getParticipants();
//            Timestamp ts = new Timestamp(info.getGameStartTimestamp());
//            Date date = new Date(ts.getTime());
            for (Participant participant : participants) {
                if (participant.getPuuid().equals(summoner.getPuuid())) {
                    List<Minute> minutes = getTimeline(platform, id, summoner.getPuuid());
                    Game game = new Game();
                    game.setChampionName(participant.getChampionName());
                    game.setKills(participant.getKills());
                    game.setDeaths(participant.getDeaths());
                    game.setAssists(participant.getAssists());
                    game.setMinutes(minutes);
                    user.getGames().add(game);
                    userRepository.save(user);
                }
            }
        }
    }

    private List<Minute> getTimeline(Platform platform, String matchId, String puuid) throws RiotApiException {
        List<Minute> minutes = new ArrayList<>();
        platform = platform.convert();
        MatchTimelineInfo info = api.getTimelineByMatchId(platform, matchId).getInfo();
        int id = 1;
        for (ParticipantIdentityInfo participantIdentityInfo : info.getParticipants()) {
            id = participantIdentityInfo.getPuuid().equals(puuid) ? participantIdentityInfo.getParticipantId() : id;
        }
        int i = 0;
        for (MatchFrame matchFrame : info.getFrames()) {
            i++;
            MatchParticipantFrame matchParticipantFrame = matchFrame.getParticipantFrames().get(id);
            Minute minute = new Minute();
            minute.setTime(i);
            minute.setTotalGold(matchParticipantFrame.getTotalGold());
            minute.setCurrentGold(matchParticipantFrame.getCurrentGold());
            minute.setMinionsKilled(matchParticipantFrame.getMinionsKilled());
            minute.setJungleMinionsKilled(matchParticipantFrame.getJungleMinionsKilled());
            minute.setPosition(matchParticipantFrame.getPosition());
            minutes.add(minute);
        }
        return minutes;
    }

}
