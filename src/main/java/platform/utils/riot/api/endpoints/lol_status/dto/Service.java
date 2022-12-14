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

package platform.utils.riot.api.endpoints.lol_status.dto;

import java.io.Serializable;
import java.util.List;

import platform.utils.riot.api.Dto;

public class Service extends Dto implements Serializable {

	private static final long serialVersionUID = -5472099748922109877L;

	private List<Incident> incidents;
	private String name;
	private String slug;
	private String status;

	public List<Incident> getIncidents() {
		return incidents;
	}

	public String getName() {
		return name;
	}

	public String getSlug() {
		return slug;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return getName();
	}
}