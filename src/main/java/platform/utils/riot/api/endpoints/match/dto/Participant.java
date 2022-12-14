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

import java.io.Serializable;
import java.util.List;

import platform.utils.riot.api.Dto;

public class Participant extends Dto implements Serializable {

	private static final long serialVersionUID = -507075680096851928L;

	private int championId;
	private String puuid;
	private String championName;
	private String summonerName;
	private String highestAchievedSeasonTier;
	private List<Mastery> masteries;
	private int participantId;
	private List<Rune> runes;
	private int spell1Id;
	private int spell2Id;
	private int goldEarned;
	private ParticipantStats stats;
	private int teamId;
	private int kills;
	private int deaths;
	private int assists;
	private int turretTakedowns;
	private ParticipantTimeline timeline;

	public int getChampionId() {
		return championId;
	}

	public String getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}

	public List<Mastery> getMasteries() {
		return masteries;
	}

	public int getParticipantId() {
		return participantId;
	}

	public List<Rune> getRunes() {
		return runes;
	}

	public int getSpell1Id() {
		return spell1Id;
	}

	public int getSpell2Id() {
		return spell2Id;
	}

	public ParticipantStats getStats() {
		return stats;
	}

	public int getTeamId() {
		return teamId;
	}

	public ParticipantTimeline getTimeline() {
		return timeline;
	}

	public int getGoldEarned() {
		return goldEarned;
	}

	public String getPuuid() {
		return puuid;
	}

	public String getChampionName() {
		return championName;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public int getAssists() {
		return assists;
	}

	public int getTurretTakedowns() {
		return turretTakedowns;
	}

	@Override
	public String toString() {
		return String.valueOf(getParticipantId());
	}
}