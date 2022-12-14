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

package platform.utils.riot.api.endpoints.static_data.dto;

import java.io.Serializable;
import java.util.Map;

import platform.utils.riot.api.Dto;

/**
 * This object contains champion list data.
 */
public class ChampionList extends Dto implements Serializable {

	private static final long serialVersionUID = -1332170960767191762L;

	private Map<String, Champion> data;
	private String format;
	private Map<String, String> keys;
	private String type;
	private String version;

	public Map<String, Champion> getData() {
		return data;
	}

	public String getFormat() {
		return format;
	}

	public Map<String, String> getKeys() {
		return keys;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return getType();
	}
}