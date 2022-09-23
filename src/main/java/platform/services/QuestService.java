package platform.services;

import platform.dtos.QuestDto;

import java.util.List;

public interface QuestService {

    List<QuestDto> findAll();

    List<QuestDto> scan(String username);
}
