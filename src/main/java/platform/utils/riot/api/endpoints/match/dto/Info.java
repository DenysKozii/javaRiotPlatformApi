/*
 * Copyright 2014 Taylor Caldwell
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

package platform.utils.riot.api.endpoints.match.dto;

import platform.utils.riot.api.Dto;

import java.io.Serializable;
import java.util.List;

public class Info extends Dto implements Serializable {

	private static final long serialVersionUID = 2206825296668330446L;

	private long gameCreation;
	private long gameStartTimestamp;
	private long gameDuration;
	private long gameId;
	private String gameMode;
	private String gameType;
	private String gameVersion;
	private int mapId;
	private List<ParticipantIdentity> participantIdentities;
	private List<Participant> participants;
	private String platformId;
	private int queueId;
	private int seasonId;
	private List<TeamStats> teams;

	public long getGameCreation() {
		return gameCreation;
	}

	public long getGameDuration() {
		return gameDuration;
	}

	public long getGameId() {
		return gameId;
	}

	public String getGameMode() {
		return gameMode;
	}

	public String getGameType() {
		return gameType;
	}

	public String getGameVersion() {
		return gameVersion;
	}

	public int getMapId() {
		return mapId;
	}

	/**
	 * Utility method to get the participant by {@code accountId}.
	 * <p>
	 * Note that Match data can be made anonymous by Riot, so in some cases it might be not possible to determine which participant is which
	 * summoner.
	 * </p>
	 * 
	 * @param accountId
	 *            Account ID
	 * @return Participant from {@link #getParticipants()} matching {@code accountId}, or {@code null} if there is no such participant.
	 */
	public Participant getParticipantByAccountId(String accountId) {
		List<ParticipantIdentity> participantIdentities = getParticipantIdentities();
		if (participantIdentities != null) {
			for (ParticipantIdentity participantIdentity : participantIdentities) {
				Player player = participantIdentity.getPlayer();
				if (player == null || player.getAccountId() == null) {
					continue;
				}
				if (player.getAccountId().equals(accountId)) {
					return getParticipantByParticipantId(participantIdentity.getParticipantId());
				}
			}
		}
		return null;
	}

	/**
	 * Utility method to get the participant by {@code participantId}.
	 * 
	 * @param participantId
	 *            Participant ID
	 * @return Participant from {@link #getParticipants()} matching {@code participantId}, or {@code null} if there is no such participant.
	 */
	public Participant getParticipantByParticipantId(int participantId) {
		List<Participant> participants = getParticipants();
		if (participants != null) {
			for (Participant participant : participants) {
				if (participant.getParticipantId() == participantId) {
					return participant;
				}
			}
		}
		return null;
	}

	/**
	 * Utility method to get the participant by {@code summonerId}.
	 * <p>
	 * Note that Match data can be made anonymous by Riot, so in some cases it might be not possible to determine which participant is which
	 * summoner.
	 * </p>
	 * 
	 * @param summonerId
	 *            Summoner ID
	 * @return Participant from {@link #getParticipants()} matching {@code summonerId}, or {@code null} if there is no such participant.
	 */
	public Participant getParticipantBySummonerId(String summonerId) {
		List<ParticipantIdentity> participantIdentities = getParticipantIdentities();
		if (participantIdentities != null) {
			for (ParticipantIdentity participantIdentity : participantIdentities) {
				Player player = participantIdentity.getPlayer();
				if (player == null) {
					continue;
				}
				if (player.getSummonerId().equals(summonerId)) {
					return getParticipantByParticipantId(participantIdentity.getParticipantId());
				}
			}
		}
		return null;
	}

	/**
	 * Utility method to get the participant by {@code summonerName}.
	 * <p>
	 * Note that Match data can be made anonymous by Riot, so in some cases it might be not possible to determine which participant is which
	 * summoner.
	 * </p>
	 * 
	 * @param summonerName
	 *            Summoner name
	 * @return Participant from {@link #getParticipants()} matching {@code summonerName}, or {@code null} if there is no such participant.
	 */
	public Participant getParticipantBySummonerName(String summonerName) {
		List<ParticipantIdentity> participantIdentities = getParticipantIdentities();
		if (participantIdentities != null) {
			for (ParticipantIdentity participantIdentity : participantIdentities) {
				Player player = participantIdentity.getPlayer();
				if (player == null) {
					continue;
				}
				if (player.getSummonerName().equalsIgnoreCase(summonerName)) {
					return getParticipantByParticipantId(participantIdentity.getParticipantId());
				}
			}
		}
		return null;
	}

	public List<ParticipantIdentity> getParticipantIdentities() {
		return participantIdentities;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public String getPlatformId() {
		return platformId;
	}

	public int getQueueId() {
		return queueId;
	}

	public int getSeasonId() {
		return seasonId;
	}

	/**
	 * Utility method to get the team by {@code teamId}.
	 * 
	 * @param teamId
	 *            Team ID
	 * @return Team from {@link #getTeams()} matching {@code teamId}, or {@code null} if there is no team matching the {@code teamId}.
	 */
	public TeamStats getTeamByTeamId(int teamId) {
		List<TeamStats> teams = getTeams();
		if (teams != null) {
			for (TeamStats team : teams) {
				if (team.getTeamId() == teamId) {
					return team;
				}
			}
		}
		return null;
	}

	public List<TeamStats> getTeams() {
		return teams;
	}

	public long getGameStartTimestamp() {
		return gameStartTimestamp;
	}

	@Override
	public String toString() {
		return String.valueOf(getGameId());
	}
}