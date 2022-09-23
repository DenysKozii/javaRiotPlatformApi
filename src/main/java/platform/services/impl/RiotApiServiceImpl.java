package platform.services.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.entities.ApiKey;
import platform.repositories.ApiKeyRepository;
import platform.services.RiotApiService;
import platform.utils.riot.api.ApiConfig;
import platform.utils.riot.api.RiotApi;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.constant.Platform;

import java.util.List;
import java.util.Optional;

@Service
public class RiotApiServiceImpl implements RiotApiService {

    private final ApiKeyRepository apiKeyRepository;
    private ApiConfig config;
    private RiotApi api;

    @Autowired
    public RiotApiServiceImpl(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
        config = new ApiConfig().setKey(getApiKey());
        api = new RiotApi(config);
    }

    @Override
    public void upsertApiKey(String apiKey) {
        apiKeyRepository.save(new ApiKey(1L, apiKey));
        config = new ApiConfig().setKey(apiKey);
        api = new RiotApi(config);
    }

    @Override
    public String getApiKey() {
        Optional<ApiKey> apiKey = apiKeyRepository.findById(1L);
        if (apiKey.isPresent()) {
            return apiKey.get().getValue();
        } else {
            throw new RuntimeException("ApiKey not found");
        }
    }

    @SneakyThrows
    @Override
    public Summoner getSummoner(Platform platform, String username) {
        return api.getSummonerByName(platform, username);
    }

    @SneakyThrows
    @Override
    public List<String> getMatchList(Platform platform, String username) {
        return api.getMatchListByAccountId(platform.convert(), getSummoner(platform, username).getPuuid());
    }

}
