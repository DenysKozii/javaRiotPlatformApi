/*
 * Copyright 2015 Taylor Caldwell
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

package platform.utils.riot.api.endpoints.spectator.dto;

import java.io.Serializable;

import platform.utils.riot.api.Dto;

public class Observer extends Dto implements Serializable {

	private static final long serialVersionUID = 1866998515867420059L;

	private String encryptionKey;

	public String getEncryptionKey() {
		return encryptionKey;
	}

	@Override
	public String toString() {
		return getEncryptionKey();
	}
}