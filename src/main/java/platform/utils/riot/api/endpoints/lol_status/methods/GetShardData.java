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

package platform.utils.riot.api.endpoints.lol_status.methods;

import platform.utils.riot.api.ApiConfig;
import platform.utils.riot.api.endpoints.lol_status.LolStatusApiMethod;
import platform.utils.riot.api.endpoints.lol_status.dto.ShardStatus;
import platform.utils.riot.constant.Platform;

public class GetShardData extends LolStatusApiMethod {

	public GetShardData(ApiConfig config, Platform platform) {
		super(config);
		setPlatform(platform);
		setReturnType(ShardStatus.class);
		setUrlBase(platform.getHost() + "/lol/status/v3/shard-data");
		addApiKeyParameter();
	}
}