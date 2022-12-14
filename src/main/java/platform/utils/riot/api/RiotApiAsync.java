/*
 * Copyright 2016 Taylor Caldwell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package platform.utils.riot.api;

import java.util.Objects;
import java.util.Set;

import platform.utils.riot.api.endpoints.league.methods.GetLeagueById;
import platform.utils.riot.api.endpoints.lol_status.methods.GetShardData;
import platform.utils.riot.api.endpoints.match.methods.GetMatch;
import platform.utils.riot.api.endpoints.match.methods.GetMatchIdsByTournamentCode;
import platform.utils.riot.api.endpoints.match.methods.GetTimelineByMatchId;
import platform.utils.riot.api.endpoints.static_data.constant.ChampionListTags;
import platform.utils.riot.api.endpoints.static_data.dto.*;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataChampionList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataLanguages;
import platform.utils.riot.api.request.AsyncRequest;
import platform.utils.riot.api.endpoints.champion.dto.ChampionInfo;
import platform.utils.riot.api.endpoints.champion.methods.GetChampionRotations;
import platform.utils.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import platform.utils.riot.api.endpoints.champion_mastery.methods.GetChampionMasteriesBySummoner;
import platform.utils.riot.api.endpoints.champion_mastery.methods.GetChampionMasteriesBySummonerByChampion;
import platform.utils.riot.api.endpoints.champion_mastery.methods.GetChampionMasteryScoresBySummoner;
import platform.utils.riot.api.endpoints.league.constant.LeagueQueue;
import platform.utils.riot.api.endpoints.league.dto.LeagueEntry;
import platform.utils.riot.api.endpoints.league.dto.LeagueList;
import platform.utils.riot.api.endpoints.league.methods.GetChallengerLeagueByQueue;
import platform.utils.riot.api.endpoints.league.methods.GetGrandmasterLeagueByQueue;
import platform.utils.riot.api.endpoints.league.methods.GetLeagueEntries;
import platform.utils.riot.api.endpoints.league.methods.GetLeagueEntriesBySummonerId;
import platform.utils.riot.api.endpoints.league.methods.GetMasterLeagueByQueue;
import platform.utils.riot.api.endpoints.lol_status.dto.ShardStatus;
import platform.utils.riot.api.endpoints.match.dto.Match;
import platform.utils.riot.api.endpoints.match.dto.MatchList;
import platform.utils.riot.api.endpoints.match.dto.MatchTimeline;
import platform.utils.riot.api.endpoints.match.methods.GetMatchByMatchIdAndTournamentCode;
import platform.utils.riot.api.endpoints.match.methods.GetMatchListByAccountId;
import platform.utils.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import platform.utils.riot.api.endpoints.spectator.dto.FeaturedGames;
import platform.utils.riot.api.endpoints.spectator.methods.GetActiveGameBySummoner;
import platform.utils.riot.api.endpoints.spectator.methods.GetFeaturedGames;
import platform.utils.riot.api.endpoints.static_data.constant.ChampionTags;
import platform.utils.riot.api.endpoints.static_data.constant.ItemListTags;
import platform.utils.riot.api.endpoints.static_data.constant.ItemTags;
import platform.utils.riot.api.endpoints.static_data.constant.Locale;
import platform.utils.riot.api.endpoints.static_data.constant.MasteryListTags;
import platform.utils.riot.api.endpoints.static_data.constant.MasteryTags;
import platform.utils.riot.api.endpoints.static_data.constant.RuneListTags;
import platform.utils.riot.api.endpoints.static_data.constant.RuneTags;
import platform.utils.riot.api.endpoints.static_data.constant.SpellListTags;
import platform.utils.riot.api.endpoints.static_data.constant.SpellTags;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataChampion;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataItem;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataItemList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataLanguageStrings;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataMaps;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataMastery;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataMasteryList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataProfileIcons;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataRealm;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataReforgedRune;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataReforgedRuneList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataReforgedRunePath;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataReforgedRunePathList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataRune;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataRuneList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataSummonerSpell;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataSummonerSpellList;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataTarballLinks;
import platform.utils.riot.api.endpoints.static_data.methods.GetDataVersions;
import platform.utils.riot.api.endpoints.summoner.dto.Summoner;
import platform.utils.riot.api.endpoints.summoner.methods.GetSummoner;
import platform.utils.riot.api.endpoints.summoner.methods.GetSummonerByAccount;
import platform.utils.riot.api.endpoints.summoner.methods.GetSummonerByName;
import platform.utils.riot.api.endpoints.summoner.methods.GetSummonerByPuuid;
import platform.utils.riot.api.endpoints.third_party_code.methods.GetThirdPartyCodeBySummoner;
import platform.utils.riot.api.endpoints.tournament.constant.PickType;
import platform.utils.riot.api.endpoints.tournament.constant.SpectatorType;
import platform.utils.riot.api.endpoints.tournament.constant.TournamentMap;
import platform.utils.riot.api.endpoints.tournament.dto.LobbyEventWrapper;
import platform.utils.riot.api.endpoints.tournament.dto.TournamentCode;
import platform.utils.riot.api.endpoints.tournament.methods.CreateTournament;
import platform.utils.riot.api.endpoints.tournament.methods.CreateTournamentCodes;
import platform.utils.riot.api.endpoints.tournament.methods.CreateTournamentProvider;
import platform.utils.riot.api.endpoints.tournament.methods.GetLobbyEventsByCode;
import platform.utils.riot.api.endpoints.tournament.methods.GetTournamentCode;
import platform.utils.riot.api.endpoints.tournament.methods.UpdateTournamentCode;
import platform.utils.riot.api.request.RequestListener;
import platform.utils.riot.constant.Platform;
import platform.utils.util.RiotApiUtil;

/**
 * This class is used to fire asynchronous requests. You can get an instance of this object by calling {@link RiotApi#getAsyncApi()} on your
 * {@code RiotApi} object.
 * 
 * <p>
 * Due to the nature of asynchronous calls, methods in this class always return an {@link AsyncRequest} instead of the actual return type.
 * To get the actual return value, call {@link AsyncRequest#getDto()}. For further information on handling asynchronous requests, see
 * {@link AsyncRequest}.
 * </p>
 *
 * @see ApiConfig
 * @see AsyncRequest
 * @see RiotApi
 */
public class RiotApiAsync {

	private final ApiConfig config;
	private final EndpointManager endpointManager;

	/**
	 * Constructs a RiotApiAsync object. This cannot be done directly. To get an RiotApiAsync object, call {@link RiotApi#getAsyncApi()} on
	 * your {@link RiotApi} object.
	 *
	 * @param config
	 *            ApiConfig object
	 * @param endpointManager
	 *            EndpointManager object
	 */
	RiotApiAsync(ApiConfig config, EndpointManager endpointManager) {
		this.config = config;
		this.endpointManager = endpointManager;
	}

	/**
	 * Adds one or more {@link RequestListener} to get informed when asynchronous requests finish.
	 * 
	 * <p>
	 * Please note, that by adding listeners here, you will listen to ALL asynchronous requests made with this RiotApiAsync object. To only
	 * listen to a specific asynchronous request, you can set listeners directly in the {@link AsyncRequest} using
	 * {@link AsyncRequest#addListeners(RequestListener...)}.
	 * </p>
	 * 
	 * @param listeners
	 *            One or more objects that implement {@link RequestListener}
	 * @see RequestListener
	 */
	public void addListeners(RequestListener... listeners) {
		endpointManager.addListeners(listeners);
	}

	/**
	 * Waits indefinitely until all currently running and queued requests complete.
	 * <p>
	 * If the thread is interrupted while waiting for the requests to complete, this method will throw an {@code InterruptedException} and
	 * the thread's interrupt flag will be cleared.
	 * </p>
	 * <p>
	 * <i>Please note that this method is blocking and thus negates the advantage of the asynchronous nature of this class. Consider using a
	 * {@link RequestListener} instead.</i>
	 * </p>
	 * 
	 * @throws InterruptedException
	 *             If the method is interrupted by calling {@link Thread#interrupt()}. The interrupt flag will be cleared
	 */
	public void awaitAll() throws InterruptedException {
		endpointManager.awaitAll();
	}

	/**
	 * Call a custom {@code ApiMethod}
	 *
	 * @param method
	 *            Custom {@code ApiMethod} object
	 * @return Result Dto (if any)
	 * @throws NullPointerException
	 *             If {@code method} is {@code null}
	 * @throws RiotApiException
	 *             If the API returns an error or unparsable result
	 */
	public AsyncRequest callCustomApiMethod(ApiMethod method) throws RiotApiException {
		Objects.requireNonNull(method);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Creates a tournament and returns its ID.
	 *
	 * @param tournamentName
	 *            The optional name of the tournament.
	 * @param providerId
	 *            The provider ID to specify the regional registered provider data to associate this tournament.
	 * @return A tournament ID
	 * @version 4
	 */
	public AsyncRequest createTournament(String tournamentName, int providerId) {
		ApiMethod method = new CreateTournament(getConfig(), tournamentName, providerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Creates a tournament and returns its ID.
	 *
	 * @param providerId
	 *            The provider ID to specify the regional registered provider data to associate this tournament.
	 * @return A tournament Id
	 * @version 4
	 */
	public AsyncRequest createTournament(int providerId) {
		return createTournament(null, providerId);
	}

	/**
	 * Create tournament codes for the given tournament.
	 *
	 * @param tournamentId
	 *            The tournament ID
	 * @param count
	 *            The number of codes to create (max 1000)
	 * @param teamSize
	 *            The team size of the game. Valid values are 1-5.
	 * @param mapType
	 *            The map type of the game.
	 * @param pickType
	 *            The pick type of the game.
	 * @param spectatorType
	 *            The spectator type of the game.
	 * @param metaData
	 *            Optional string that may contain any data in any format, if specified at all. Used to denote any custom information about
	 *            the game.
	 * @param allowedSummonerIds
	 *            Optional list of participants in order to validate the players eligible to join the lobby.
	 * @return A list of tournament codes
	 * @throws NullPointerException
	 *             If {@code mapType} or {@code pickType} or {@code spectatorType} is {@code null}
	 * @version 4
	 */
	public AsyncRequest createTournamentCodes(int tournamentId, int count, int teamSize, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			String metaData, String... allowedSummonerIds) {
		Objects.requireNonNull(mapType);
		Objects.requireNonNull(pickType);
		Objects.requireNonNull(spectatorType);
		ApiMethod method = new CreateTournamentCodes(getConfig(), tournamentId, count, teamSize, mapType, pickType, spectatorType, metaData,
				allowedSummonerIds);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Create tournament codes for the given tournament.
	 *
	 * @param tournamentId
	 *            The tournament ID
	 * @param count
	 *            The number of codes to create (max 1000)
	 * @param teamSize
	 *            The team size of the game. Valid values are 1-5.
	 * @param mapType
	 *            The map type of the game.
	 * @param pickType
	 *            The pick type of the game.
	 * @param spectatorType
	 *            The spectator type of the game.
	 * @param allowedSummonerIds
	 *            Optional list of participants in order to validate the players eligible to join the lobby.
	 * @return A list of tournament codes
	 * @version 4
	 */
	public AsyncRequest createTournamentCodes(int tournamentId, int count, int teamSize, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			String... allowedSummonerIds) {
		return createTournamentCodes(tournamentId, count, teamSize, mapType, pickType, spectatorType, null, allowedSummonerIds);
	}

	/**
	 * Creates a tournament provider and returns its ID.
	 *
	 * @param region
	 *            The region in which the provider will be running tournaments.
	 * @param callbackUrl
	 *            The provider's callback URL to which tournament game results in this region should be posted. (http URLs must use port 80,
	 *            https URLs must use port 443).
	 * @return A provider ID
	 * @throws NullPointerException
	 *             If {@code region} or {@code callbackUrl} is {@code null}
	 * @version 4
	 */
	public AsyncRequest createTournamentProvider(String region, String callbackUrl) {
		Objects.requireNonNull(region);
		Objects.requireNonNull(callbackUrl);
		ApiMethod method = new CreateTournamentProvider(getConfig(), region, callbackUrl);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get current game information for the given summoner ID.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            The ID of the summoner.
	 * @return Current game info
	 * @throws NullPointerException
	 *             If {@code platform} or {@code summonerId} is {@code null}
	 * @version 4
	 * @see CurrentGameInfo
	 */
	public AsyncRequest getActiveGameBySummoner(Platform platform, String summonerId) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(summonerId);
		ApiMethod method = new GetActiveGameBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the challenger league for a given {@code queue}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queue
	 *            Game queue type.
	 * @return A league list
	 * @throws NullPointerException
	 *             If {@code platform} or {@code queue} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getChallengerLeagueByQueue(Platform platform, String queue) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(queue);
		ApiMethod method = new GetChallengerLeagueByQueue(getConfig(), platform, queue);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the challenger league for a given {@code queue}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queue
	 *            Game queue type.
	 * @return A league list
	 * @throws NullPointerException
	 *             If {@code queue} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getChallengerLeagueByQueue(Platform platform, LeagueQueue queue) {
		Objects.requireNonNull(queue);
		return getChallengerLeagueByQueue(platform, queue.toString());
	}

	/**
	 * Get all champion mastery entries sorted by number of champion points descending
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            Summoner ID associated with the player
	 * @return A list of champion masteries for a given summoner.
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see ChampionMastery
	 */
	public AsyncRequest getChampionMasteriesBySummoner(Platform platform, String summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionMasteriesBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get a champion mastery by {@code summonerId} and {@code championId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            Summoner ID associated with the player
	 * @param championId
	 *            Champion ID to retrieve Champion Mastery for
	 * @return Champion mastery for a given summoner and championId, or {@code null} if given player has no mastery for given champion.
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see ChampionMastery
	 */
	public AsyncRequest getChampionMasteriesBySummonerByChampion(Platform platform, String summonerId, int championId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionMasteriesBySummonerByChampion(getConfig(), platform, summonerId, championId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get a player's total champion mastery score, which is sum of individual champion mastery levels
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            Summoner ID associated with the player
	 * @return The total champion mastery score of a given summoner.
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 */
	public AsyncRequest getChampionMasteryScoresBySummoner(Platform platform, String summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionMasteryScoresBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Returns champion rotations, including free-to-play and low-level free-to-play rotations.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return This object contains information about champion rotations.
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ChampionInfo
	 */
	public AsyncRequest getChampionRotations(Platform platform) throws RiotApiException {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionRotations(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the configuration
	 * 
	 * @return {@link ApiConfig} object
	 * @see ApiConfig
	 */
	protected ApiConfig getConfig() {
		return config;
	}

	/**
	 * Retrieves a champion by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Champion ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code id}, {@code key}, {@code name}, and {@code title} are returned by default if
	 *            this parameter isn't specified. To return all additional data, use {@code ChampionTags.ALL}.
	 * @return A single champion
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see Champion
	 */
	public AsyncRequest getDataChampion(Platform platform, int id, Locale locale, String version, ChampionTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataChampion(getConfig(), platform, id, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves a champion by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Champion ID
	 * @return A single champion
	 * @version 3
	 * @see Champion
	 */
	public AsyncRequest getDataChampion(Platform platform, int id) {
		return getDataChampion(platform, id, null, null);
	}

	/**
	 * Retrieves champion list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param dataById
	 *            If specified as true, the returned data map will use the champions' IDs as the keys. If specified as false, the returned
	 *            data map will use the champions' keys instead.
	 * @param tags
	 *            Tags to return additional data. Only {@code id}, {@code key}, {@code name}, and {@code title} are returned by default if
	 *            this parameter isn't specified. To return all additional data, use {@code ChampionListTags.ALL}.
	 * @return A list with champions
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ChampionList
	 */
	public AsyncRequest getDataChampionList(Platform platform, Locale locale, String version, boolean dataById, ChampionListTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataChampionList(getConfig(), platform, locale, version, dataById, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves champion list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list with champions
	 * @version 3
	 * @see ChampionList
	 */
	public AsyncRequest getDataChampionList(Platform platform) {
		return getDataChampionList(platform, null, null, false);
	}

	/**
	 * Retrieves item by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Item ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code type}, {@code version}, {@code basic}, {@code data}, {@code id}, {@code name},
	 *            {@code plaintext}, {@code group}, and {@code description} are returned by default if this parameter isn't specified. To
	 *            return all additional data, use {@code ItemTags.ALL}.
	 * @return A single item
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see Item
	 */
	public AsyncRequest getDataItem(Platform platform, int id, Locale locale, String version, ItemTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataItem(getConfig(), platform, id, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves item by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Item ID
	 * @return A single item
	 * @version 3
	 * @see Item
	 */
	public AsyncRequest getDataItem(Platform platform, int id) {
		return getDataItem(platform, id, null, null);
	}

	/**
	 * Retrieves item list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code type}, {@code version}, {@code basic}, {@code data}, {@code id}, {@code name},
	 *            {@code plaintext}, {@code group}, and {@code description} are returned by default if this parameter isn't specified. To
	 *            return all additional data, use {@code ItemListTags.ALL}.
	 * @return A list of items
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ItemList
	 */
	public AsyncRequest getDataItemList(Platform platform, Locale locale, String version, ItemListTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataItemList(getConfig(), platform, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves item list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list of items
	 * @version 3
	 * @see ItemList
	 */
	public AsyncRequest getDataItemList(Platform platform) {
		return getDataItemList(platform, null, null);
	}

	/**
	 * Retrieve supported languages data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list with languages
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 */
	public AsyncRequest getDataLanguages(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataLanguages(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieve language strings data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return Language strings
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see LanguageStrings
	 */
	public AsyncRequest getDataLanguageStrings(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataLanguageStrings(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieve language strings data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return Language strings
	 * @version 3
	 * @see LanguageStrings
	 */
	public AsyncRequest getDataLanguageStrings(Platform platform) {
		return getDataLanguageStrings(platform, null, null);
	}

	/**
	 * Retrieves map data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return A list of game maps
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see MapData
	 */
	public AsyncRequest getDataMaps(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataMaps(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves map data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list of game maps
	 * @version 3
	 * @see MapData
	 */
	public AsyncRequest getDataMaps(Platform platform) {
		return getDataMaps(platform, null, null);
	}

	/**
	 * Retrieves mastery item by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Mastery ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code id}, {@code name}, and {@code description} are returned by default if this
	 *            parameter isn't specified. To return all additional data, use {@code MasteryTags.ALL}.
	 * @return A single mastery
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see Mastery
	 */
	public AsyncRequest getDataMastery(Platform platform, int id, Locale locale, String version, MasteryTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataMastery(getConfig(), platform, id, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves mastery item by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Mastery ID
	 * @return A single mastery
	 * @version 3
	 * @see Mastery
	 */
	public AsyncRequest getDataMastery(Platform platform, int id) {
		return getDataMastery(platform, id, null, null);
	}

	/**
	 * Retrieves mastery list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code type}, {@code version}, {@code data}, {@code id}, {@code name}, and
	 *            {@code description} are returned by default if this parameter isn't specified. To return all additional data, use
	 *            {@code MasteryListTags.ALL}.
	 * @return A list with masteries
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see MasteryList
	 */
	public AsyncRequest getDataMasteryList(Platform platform, Locale locale, String version, MasteryListTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataMasteryList(getConfig(), platform, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves mastery list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list with masteries
	 * @version 3
	 * @see MasteryList
	 */
	public AsyncRequest getDataMasteryList(Platform platform) {
		return getDataMasteryList(platform, null, null);
	}

	/**
	 * Retrieve profile icons.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return Profile icons
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ProfileIconData
	 */
	public AsyncRequest getDataProfileIcons(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataProfileIcons(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieve profile icons.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return Profile icons
	 * @version 3
	 * @see ProfileIconData
	 */
	public AsyncRequest getDataProfileIcons(Platform platform) {
		return getDataProfileIcons(platform, null, null);
	}

	/**
	 * Retrieve realm data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A single realm
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see Realm
	 */
	public AsyncRequest getDataRealm(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataRealm(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves reforged rune by {@code id} asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Reforged rune ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return A single reforged rune
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ReforgedRune
	 */
	public AsyncRequest getDataReforgedRune(Platform platform, int id, Locale locale, String version) throws RiotApiException {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataReforgedRune(getConfig(), platform, id, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves reforged rune by {@code id} asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Reforged rune ID
	 * @return A single reforged rune
	 * @version 3
	 * @see ReforgedRune
	 */
	public AsyncRequest getDataReforgedRune(Platform platform, int id) throws RiotApiException {
		return getDataReforgedRune(platform, id, null, null);
	}

	/**
	 * Retrieves reforged rune array asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return An array of reforged runes
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ReforgedRune
	 */
	public AsyncRequest getDataReforgedRuneList(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataReforgedRuneList(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves reforged rune array asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return An array of reforged runes
	 * @version 3
	 * @see ReforgedRune
	 */
	public AsyncRequest getDataReforgedRuneList(Platform platform) {
		return getDataReforgedRuneList(platform, null, null);
	}

	/**
	 * Retrieves reforged rune path by {@code id} asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Reforged rune path ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return A single reforged rune path
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ReforgedRunePath
	 */
	public AsyncRequest getDataReforgedRunePath(Platform platform, int id, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataReforgedRunePath(getConfig(), platform, id, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves reforged rune path by {@code id} asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Reforged rune path ID
	 * @return A single reforged rune path
	 * @version 3
	 * @see ReforgedRunePath
	 */
	public AsyncRequest getDataReforgedRunePath(Platform platform, int id) {
		return getDataReforgedRunePath(platform, id, null, null);
	}

	/**
	 * Retrieves reforged rune path array asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @return An array of reforged runes path
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ReforgedRunePath
	 */
	public AsyncRequest getDataReforgedRunePathList(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataReforgedRunePathList(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves reforged rune path array asynchronously.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return An array of reforged runes path
	 * @version 3
	 * @see ReforgedRunePath
	 */
	public AsyncRequest getDataReforgedRunePathList(Platform platform) {
		return getDataReforgedRunePathList(platform, null, null);
	}

	/**
	 * Retrieves rune by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Rune ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code id}, {@code name}, {@code rune}, and {@code description} are returned by
	 *            default if this parameter isn't specified. To return all additional data, use {@code RuneTags.ALL}.
	 * @return A single rune
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see Rune
	 */
	public AsyncRequest getDataRune(Platform platform, int id, Locale locale, String version, RuneTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataRune(getConfig(), platform, id, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves rune by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Rune ID
	 * @return A single rune
	 * @version 3
	 * @see Rune
	 */
	public AsyncRequest getDataRune(Platform platform, int id) {
		return getDataRune(platform, id, null, null);
	}

	/**
	 * Retrieves rune list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code type}, {@code version}, {@code data}, {@code id}, {@code name}, {@code rune},
	 *            and {@code description} are returned by default if this parameter isn't specified. To return all additional data, use
	 *            {@code RuneListTags.ALL}.
	 * @return A list with runes
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see RuneList
	 */
	public AsyncRequest getDataRuneList(Platform platform, Locale locale, String version, RuneListTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataRuneList(getConfig(), platform, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves rune list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list with runes
	 * @version 3
	 * @see RuneList
	 */
	public AsyncRequest getDataRuneList(Platform platform) {
		return getDataRuneList(platform, null, null);
	}

	/**
	 * Retrieves summoner spell by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Summoner spell ID
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param tags
	 *            Tags to return additional data. Only {@code id}, {@code key}, {@code name}, {@code description}, and {@code summonerLevel}
	 *            are returned by default if this parameter isn't specified. To return all additional data, use {@code SpellTags.ALL}.
	 * @return A single summoner spell
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see SummonerSpell
	 */
	public AsyncRequest getDataSummonerSpell(Platform platform, int id, Locale locale, String version, SpellTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataSummonerSpell(getConfig(), platform, id, locale, version, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves summoner spell by {@code id}.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param id
	 *            Summoner spell ID
	 * @return A single summoner spell
	 * @version 3
	 * @see SummonerSpell
	 */
	public AsyncRequest getDataSummonerSpell(Platform platform, int id) {
		return getDataSummonerSpell(platform, id, null, null);
	}

	/**
	 * Retrieves summoner spell list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param locale
	 *            Locale code for returned data (e.g., {@code en_US}, {@code es_ES}). If not specified, the default locale for the region is
	 *            used.
	 * @param version
	 *            Data dragon version for returned data. If not specified, the latest version for the region is used. List of valid versions
	 *            can be obtained from the {@link #getDataVersions()} method.
	 * @param dataById
	 *            If specified as true, the returned data map will use the spells' IDs as the keys. If specified as false, the returned data
	 *            map will use the spells' keys instead
	 * @param tags
	 *            Tags to return additional data. Only {@code type}, {@code version}, {@code data}, {@code id}, {@code key}, {@code name},
	 *            {@code description}, and {@code summonerLevel} are returned by default if this parameter isn't specified. To return all
	 *            additional data, use {@code SpellListTags.ALL}.
	 * @return A list with summoner spells
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see SummonerSpellList
	 */
	public AsyncRequest getDataSummonerSpellList(Platform platform, Locale locale, String version, boolean dataById, SpellListTags... tags) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataSummonerSpellList(getConfig(), platform, locale, version, dataById, tags);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves summoner spell list.
	 * <p>
	 * <i>Not all data is returned by default. See the tags parameter for more information.</i>
	 * </p>
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list with summoner spells
	 * @version 3
	 * @see SummonerSpellList
	 */
	public AsyncRequest getDataSummonerSpellList(Platform platform) {
		return getDataSummonerSpellList(platform, null, null, false);
	}

	/**
	 * Retrieves full tarball link.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param version
	 *            Patch version for returned data. If not specified, the latest version is used. List of valid versions can be obtained from
	 *            {@link #getDataVersions()}.
	 * @return Tarball link
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 */
	public AsyncRequest getDataTarballLinks(Platform platform, String version) throws RiotApiException {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataTarballLinks(getConfig(), platform, version);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieves full tarball link.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return Tarball link
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 */
	public AsyncRequest getDataTarballLinks(Platform platform) throws RiotApiException {
		Objects.requireNonNull(platform);
		return getDataTarballLinks(platform, null);
	}

	/**
	 * Retrieve version data.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return A list with versions
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 */
	public AsyncRequest getDataVersions(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataVersions(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get list of featured games.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return Featured games
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see FeaturedGames
	 */
	public AsyncRequest getFeaturedGames(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetFeaturedGames(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the grandmaster league for a given {@code queue}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queueType
	 *            Game queue type.
	 * @return A league list
	 * @throws NullPointerException
	 *             If {@code platform} or {@code queue} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getGrandmasterLeagueByQueue(Platform platform, String queue) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(queue);
		ApiMethod method = new GetGrandmasterLeagueByQueue(getConfig(), platform, queue);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the grandmaster league for a given {@code queue}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queue
	 *            Game queue type.
	 * @return A league list
	 * @throws NullPointerException
	 *             If {@code queue} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getGrandmasterLeagueByQueue(Platform platform, LeagueQueue queue) {
		Objects.requireNonNull(queue);
		return getGrandmasterLeagueByQueue(platform, queue.toString());
	}

	/**
	 * Get league with given ID, including inactive entries.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param leagueId
	 *            League ID
	 * @return League list
	 * @throws NullPointerException
	 *             If {@code platform} or {@code leagueId} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getLeagueById(Platform platform, String leagueId) throws RiotApiException {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(leagueId);
		ApiMethod method = new GetLeagueById(getConfig(), platform, leagueId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get league entries in all queues for a given {@code queue}, {@code tier}, and {@code division}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queue
	 *            Ranked Queue
	 * @param tier
	 *            Tier
	 * @param division
	 *            Division
	 * @return List of league entries
	 * @throws NullPointerException
	 *             If {@code platform}, {@code queue}, {@code tier}, or {@code division} is {@code null}
	 * @version 4
	 * @see LeagueEntry
	 */
	public AsyncRequest getLeagueEntries(Platform platform, String queue, String tier, String division) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(queue);
		Objects.requireNonNull(tier);
		Objects.requireNonNull(division);
		ApiMethod method = new GetLeagueEntries(getConfig(), platform, queue, tier, division);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get league entries in all queues for a given {@code summonerId}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            Summoner ID
	 * @return List of league entries
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see LeagueEntry
	 */
	public AsyncRequest getLeagueEntriesBySummonerId(Platform platform, String summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetLeagueEntriesBySummonerId(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Gets a list of lobby events by {@code tournamentCode}
	 * 
	 * @param tournamentCode
	 *            Tournament code used to enter the lobby.
	 * @return Lobby event data
	 * @throws NullPointerException
	 *             If {@code tournamentCode} is {@code null}
	 * @version 4
	 * @see LobbyEventWrapper
	 */
	public AsyncRequest getLobbyEventsByTournament(String tournamentCode) {
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetLobbyEventsByCode(getConfig(), tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the master league for a given {@code queue}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queueType
	 *            Game queue type.
	 * @return A league list
	 * @throws NullPointerException
	 *             If {@code platform} or {@code queue} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getMasterLeagueByQueue(Platform platform, String queue) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(queue);
		ApiMethod method = new GetMasterLeagueByQueue(getConfig(), platform, queue);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the master league for a given {@code queue}.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param queue
	 *            Game queue type.
	 * @return A league list
	 * @throws NullPointerException
	 *             If {@code queue} is {@code null}
	 * @version 4
	 * @see LeagueList
	 */
	public AsyncRequest getMasterLeagueByQueue(Platform platform, LeagueQueue queue) {
		Objects.requireNonNull(queue);
		return getMasterLeagueByQueue(platform, queue.toString());
	}

	/**
	 * Get match by {@code matchId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param matchId
	 *            The ID of the match.
	 * @param forAccountId
	 *            If provided, used to identify the participant to be unobfuscated.
	 * @return A map with match details
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see Match
	 */
	public AsyncRequest getMatch(Platform platform, String matchId, String forAccountId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetMatch(getConfig(), platform, matchId, forAccountId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get match by {@code matchId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param matchId
	 *            The ID of the match.
	 * @return A map with match details
	 * @version 4
	 * @see Match
	 */
	public AsyncRequest getMatch(Platform platform, String matchId) {
		return getMatch(platform, matchId, null);
	}

	/**
	 * Retrieve match IDs by {@code tournamentCode}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param tournamentCode
	 *            The code of the tournament.
	 * @return A list of match IDs
	 * @throws NullPointerException
	 *             If {@code platform} or {@code tournamentCode} is {@code null}
	 * @version 4
	 */
	public AsyncRequest getMatchIdsByTournamentCode(Platform platform, String tournamentCode) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetMatchIdsByTournamentCode(getConfig(), platform, tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Retrieve match by {@code matchId} and {@code tournamentCode}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param matchId
	 *            The ID of the match.
	 * @param tournamentCode
	 *            The code of the tournament.
	 * @return A map with match details
	 * @throws NullPointerException
	 *             If {@code platform} or {@code tournamentCode} is {@code null}
	 * @version 4
	 * @see Match
	 */
	public AsyncRequest getMatchByMatchIdAndTournamentCode(Platform platform, long matchId, String tournamentCode) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetMatchByMatchIdAndTournamentCode(getConfig(), platform, matchId, tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get matchlist for given account ID and platform ID.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param accountId
	 *            The account ID of the summoner.
	 * @param champion
	 *            Set of champion IDs for which to filtering matchlist.
	 * @param queue
	 *            Set of queue IDs for which to filtering matchlist.
	 * @param season
	 *            Set of season IDs for which to filtering matchlist.
	 * @param beginTime
	 *            The begin time to use for filtering matchlist specified as epoch milliseconds. Use {@code -1} to not use this parameter.
	 * @param endTime
	 *            The end time to use for filtering matchlist specified as epoch milliseconds. Use {@code -1} to not use this parameter.
	 * @param beginIndex
	 *            The begin index to use for filtering matchlist. Use {@code -1} to not use this parameter.
	 * @param endIndex
	 *            The end index to use for filtering matchlist. Use {@code -1} to not use this parameter.
	 * @return A list with matches
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see MatchList
	 */
	public AsyncRequest getMatchListByAccountId(Platform platform, String accountId, Set<Integer> champion, Set<Integer> queue, Set<Integer> season,
			long beginTime, long endTime, int beginIndex, int endIndex) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetMatchListByAccountId(getConfig(), platform, accountId, champion, queue, season, beginTime, endTime, beginIndex, endIndex);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get matchlist for given account ID and platform ID.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param accountId
	 *            The account ID of the summoner.
	 * @param champion
	 *            Set of champion IDs for which to filtering matchlist.
	 * @param queue
	 *            Set of queue IDs for which to filtering matchlist.
	 * @param season
	 *            Set of season IDs for which to filtering matchlist.
	 * @return A list with matches
	 * @version 4
	 * @see MatchList
	 */
	public AsyncRequest getMatchListByAccountId(Platform platform, String accountId, Set<Integer> champion, Set<Integer> queue, Set<Integer> season) {
		return getMatchListByAccountId(platform, accountId, champion, queue, season, -1, -1, -1, -1);
	}

	/**
	 * Get matchlist for given account ID and platform ID.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param accountId
	 *            The account ID of the summoner.
	 * @return A list with matches
	 * @version 4
	 * @see MatchList
	 */
	public AsyncRequest getMatchListByAccountId(Platform platform, String accountId) {
		return getMatchListByAccountId(platform, accountId, null, null, null);
	}

	/**
	 * Returns the number of elements in the asynchronous request pool.
	 *
	 * @return Number of elements in the asynchronous request pool
	 */
	public int getPoolSize() {
		return endpointManager.getPoolSize();
	}

	/**
	 * Returns the number of elements in the asynchronous request queue.
	 *
	 * @return Number of elements in the asynchronous request queue
	 */
	public int getQueueSize() {
		return endpointManager.getQueueSize();
	}

	/**
	 * Get shard status. Returns the data available on the status.leagueoflegends.com website for the given region.
	 * 
	 * @param platform
	 *            Platform to execute the method call against.
	 * @return Status for a single shard
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 3
	 * @see ShardStatus
	 */
	public AsyncRequest getShardData(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetShardData(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get a summoner object for a given {@code accountId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param accountId
	 *            Account ID associated with summoner to retrieve.
	 * @return The desired summoner
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see Summoner
	 */
	public AsyncRequest getSummonerByAccount(Platform platform, String accountId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetSummonerByAccount(getConfig(), platform, accountId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get a summoner object for a given {@code summonerId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            Summoner ID associated with summoner to retrieve.
	 * @return The desired summoner
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see Summoner
	 */
	public AsyncRequest getSummoner(Platform platform, String summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetSummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get a single summoner object for a given {@code summonerName}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerName
	 *            Summoner name associated with summoner to retrieve.
	 * @return A map of desired summoners
	 * @throws IllegalArgumentException
	 *             If {@code summonerName} is not a valid summoner name
	 * @throws NullPointerException
	 *             If {@code platform} or {@code summonerName} is {@code null}
	 * @version 4
	 * @see Summoner
	 */
	public AsyncRequest getSummonerByName(Platform platform, String summonerName) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(summonerName);
		RiotApiUtil.requireValidSummonerName(summonerName);
		ApiMethod method = new GetSummonerByName(getConfig(), platform, summonerName);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get a summoner object for a given {@code puuid}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param puuid
	 *            PUUID associated with summoner to retrieve.
	 * @return The desired summoner
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see Summoner
	 */
	public AsyncRequest getSummonerByPuuid(Platform platform, String puuid) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetSummonerByPuuid(getConfig(), platform, puuid);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get the third party code for a given {@code summonerId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param summonerId
	 *            Summoner ID associated with summoner to retrieve third party code for.
	 * @return Third party code of the desired summoner
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 */
	public AsyncRequest getThirdPartyCodeBySummoner(Platform platform, String summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetThirdPartyCodeBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Get match timeline by {@code matchId}.
	 *
	 * @param platform
	 *            Platform to execute the method call against.
	 * @param matchId
	 *            The ID of the match.
	 * @return A map with match timeline details
	 * @throws NullPointerException
	 *             If {@code platform} is {@code null}
	 * @version 4
	 * @see MatchTimeline
	 */
	public AsyncRequest getTimelineByMatchId(Platform platform, String matchId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetTimelineByMatchId(getConfig(), platform, matchId);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Returns the tournament code DTO associated with a {@code tournamentCode}.
	 *
	 * @param tournamentCode
	 *            The tournament code
	 * @return Tournament code DTO
	 * @throws NullPointerException
	 *             If {@code tournamentCode} is {@code null}
	 * @version 4
	 * @see TournamentCode
	 */
	public AsyncRequest getTournamentCode(String tournamentCode) {
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetTournamentCode(getConfig(), tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}

	/**
	 * Removes one or more {@link RequestListener} from getting informed when asynchronous requests finish.
	 * 
	 * @param listeners
	 *            One or more objects that implement {@link RequestListener}
	 * @see RequestListener
	 */
	public void removeListeners(RequestListener... listeners) {
		endpointManager.removeListeners(listeners);
	}

	/**
	 * Update the pick type, map, spectator type, or allowed summoners for a code.
	 *
	 * @param tournamentCode
	 *            The tournament code
	 * @param mapType
	 *            The map type of the game.
	 * @param pickType
	 *            The pick type of the game.
	 * @param spectatorType
	 *            The spectator type of the game.
	 * @param allowedSummonerIds
	 *            Optional list of participants in order to validate the players eligible to join the lobby.
	 * @throws NullPointerException
	 *             If {@code tournamentCode} is {@code null}
	 * @version 4
	 */
	public void updateTournamentCode(String tournamentCode, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			String... allowedSummonerIds) {
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new UpdateTournamentCode(getConfig(), tournamentCode, mapType, pickType, spectatorType, allowedSummonerIds);
		endpointManager.callMethodAsynchronously(method);
	}
}