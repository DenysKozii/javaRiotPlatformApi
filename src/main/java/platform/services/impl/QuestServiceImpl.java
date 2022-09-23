package platform.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import platform.dtos.QuestDto;
import platform.mappers.QuestMapper;
import platform.repositories.QuestRepository;
import platform.services.QuestService;
import platform.services.RiotApiService;

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
    public List<QuestDto> scan(String username) {
        riotApiService.getApiKey();
        return null;
    }
}
