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

public class MatchTimelineInfo extends Dto implements Serializable {

    private long frameInterval;
    private List<MatchFrame> frames;
    private List<ParticipantIdentityInfo> participants;

    public long getFrameInterval() {
        return frameInterval;
    }

    public List<MatchFrame> getFrames() {
        return frames;
    }

    public List<ParticipantIdentityInfo> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return String.valueOf(getFrameInterval());
    }
}