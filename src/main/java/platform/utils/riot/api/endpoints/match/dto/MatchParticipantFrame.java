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

public class MatchParticipantFrame extends Dto implements Serializable {

	private int currentGold;
	private int dominionScore;
	private int jungleMinionsKilled;
	private int level;
	private int minionsKilled;
	private int participantId;
	private MatchPosition position;
	private int teamScore;
	private int totalGold;
	private int xp;

	public int getCurrentGold() {
		return currentGold;
	}

	public int getDominionScore() {
		return dominionScore;
	}

	public int getJungleMinionsKilled() {
		return jungleMinionsKilled;
	}

	public int getLevel() {
		return level;
	}

	public int getMinionsKilled() {
		return minionsKilled;
	}

	public int getParticipantId() {
		return participantId;
	}

	public MatchPosition getPosition() {
		return position;
	}

	public int getTeamScore() {
		return teamScore;
	}

	public int getTotalGold() {
		return totalGold;
	}

	public int getXp() {
		return xp;
	}
}