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

public class TeamStats extends Dto implements Serializable {

	private static final long serialVersionUID = -3414402914522684688L;

	private List<TeamBans> bans;
	private int baronKills;
	private int dominionVictoryScore;
	private int dragonKills;
	private boolean firstBaron;
	private boolean firstBlood;
	private boolean firstDragon;
	private boolean firstInhibitor;
	private boolean firstRiftHerald;
	private boolean firstTower;
	private int inhibitorKills;
	private int riftHeraldKills;
	private int teamId;
	private int towerKills;
	private int vilemawKills;
	private String win;

	public List<TeamBans> getBans() {
		return bans;
	}

	public int getBaronKills() {
		return baronKills;
	}

	public int getDominionVictoryScore() {
		return dominionVictoryScore;
	}

	public int getDragonKills() {
		return dragonKills;
	}

	public int getInhibitorKills() {
		return inhibitorKills;
	}

	public int getRiftHeraldKills() {
		return riftHeraldKills;
	}

	public int getTeamId() {
		return teamId;
	}

	public int getTowerKills() {
		return towerKills;
	}

	public int getVilemawKills() {
		return vilemawKills;
	}

	public String getWin() {
		return win;
	}

	public boolean isFirstBaron() {
		return firstBaron;
	}

	public boolean isFirstBlood() {
		return firstBlood;
	}

	public boolean isFirstDragon() {
		return firstDragon;
	}

	public boolean isFirstInhibitor() {
		return firstInhibitor;
	}

	public boolean isFirstRiftHerald() {
		return firstRiftHerald;
	}

	public boolean isFirstTower() {
		return firstTower;
	}

	@Override
	public String toString() {
		return String.valueOf(getTeamId());
	}
}