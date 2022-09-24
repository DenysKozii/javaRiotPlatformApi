package platform.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.dtos.QuestDto;
import platform.entities.Quest;
import platform.enums.QuestType;
import platform.mappers.QuestMapper;
import platform.repositories.QuestRepository;
import platform.services.QuestService;
import platform.services.RiotApiService;
import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.constant.Platform;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;
    private final RiotApiService riotApiService;

    @Override
    public List<QuestDto> findAll() {
        return questRepository.findAll().stream()
                .map(QuestMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Match> scan(Platform platform, String username) {
        List<String> matchIds = riotApiService.getMatchList(platform, username);
        return matchIds.stream()
                .map(id -> riotApiService.getMatch(platform.convert(), id))
                .peek(o -> System.out.println(o.getInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public void create(QuestDto questDto) {
        Quest quest = new Quest();
        quest.setTitle(questDto.getTitle());
        quest.setType(questDto.getType());
        quest.setPrice(questDto.getPrice());
        quest.setTrigger(questDto.getTrigger());
        quest.setProfit(questDto.getProfit());
        questRepository.save(quest);
    }

    @Override
    public boolean select(Platform platform, String username, String questId) {
        return false;
    }

    @Override
    public List<QuestDto> available(Platform platform, String username) {
        return findAll();
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initialise() {
        if (questRepository.count() == 0) {
            create(new QuestDto("Farm at least 120 minions", 0, 1, 120, QuestType.FARM_1));
            create(new QuestDto("Farm at least 150 minions", 1, 1, 150, QuestType.FARM_2));
            create(new QuestDto("Farm at least 200 minions", 2, 1, 200, QuestType.FARM_3));
        }
    }
}
