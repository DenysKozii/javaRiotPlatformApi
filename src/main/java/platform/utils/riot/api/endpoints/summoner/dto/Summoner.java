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

package platform.utils.riot.api.endpoints.summoner.dto;

import java.io.Serializable;

import platform.utils.riot.api.Dto;

/**
 * represents a summoner
 */
public class Summoner extends Dto implements Serializable {

	private static final long serialVersionUID = -8213488199644701555L;

	private String accountId;
	private String id;
	private String puuid;
	private String name;
	private int profileIconId;
	private long revisionDate;
	private int summonerLevel;

	public String getAccountId() {
		return accountId;
	}

	public String getId() {
		return id;
	}

	public String getPuuid() {
		return puuid;
	}

	public String getName() {
		return name;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public long getRevisionDate() {
		return revisionDate;
	}

	public int getSummonerLevel() {
		return summonerLevel;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}