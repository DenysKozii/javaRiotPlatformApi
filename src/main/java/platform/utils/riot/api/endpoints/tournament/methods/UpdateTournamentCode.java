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

package platform.utils.riot.api.endpoints.tournament.methods;

import java.util.HashMap;
import java.util.Map;

import platform.utils.riot.api.ApiConfig;
import platform.utils.riot.api.endpoints.tournament.constant.TournamentMap;
import platform.utils.riot.api.endpoints.tournament.TournamentApiMethod;
import platform.utils.riot.api.endpoints.tournament.constant.PickType;
import platform.utils.riot.api.endpoints.tournament.constant.SpectatorType;
import platform.utils.riot.api.request.RequestMethod;

public class UpdateTournamentCode extends TournamentApiMethod {

	public UpdateTournamentCode(ApiConfig config, String tournamentCode, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
								String... allowedSummonerIds) {
		super(config);
		setHttpMethod(RequestMethod.PUT);
		setReturnType(Void.class);
		setUrlBase(HOST + "/lol/tournament/v3/codes/" + tournamentCode);
		addTournamentApiKeyParameter();

		Map<String, Object> body = new HashMap<String, Object>();
		if (mapType != null) {
			body.put("mapType", mapType);
		}
		if (pickType != null) {
			body.put("pickType", pickType);
		}
		if (spectatorType != null) {
			body.put("spectatorType", spectatorType);
		}
		if (allowedSummonerIds != null && allowedSummonerIds.length > 0) {
			body.put("allowedSummonerIds", allowedSummonerIds);
		}
		buildJsonBody(body);
	}
}