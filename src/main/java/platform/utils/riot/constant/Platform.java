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

package platform.utils.riot.constant;

import java.util.NoSuchElementException;

import platform.utils.riot.api.RiotApi;

public enum Platform {
    BR("BR1", "br"),
    EUNE("EUN1", "eune"),
    EUW("EUW1", "euw"),
    JP("JP1", "jp"),
    KR("KR", "kr"),
    LAN("LA1", "lan"),
    LAS("LA2", "las"),
    NA("NA1", "na"),
    OCE("OC1", "oce"),
    RU("RU", "ru"),
    TR("TR1", "tr"),
    AMERICA("AMERICA", "AMERICA"),
    ASIA("ASIA", "ASIA"),
    SEA("SEA", "SEA"),
    EUROPE("EUROPE", "EUROPE");

    private String id;
    private String name;

    public Platform convert() {
        switch (this) {
            case SEA:
                return SEA;
            case BR:
            case NA:
            case EUNE:
            case AMERICA:
                return AMERICA;
            case EUW:
            case RU:
            case EUROPE:
            default:
                return EUROPE;
        }
    }

    public static Platform getPlatformById(String id) {
        for (Platform platform : Platform.values()) {
            if (platform.getId().equalsIgnoreCase(id)) {
                return platform;
            }
        }
        RiotApi.log.warning("Unknown platform ID: " + id);
        throw new NoSuchElementException("Unknown platform ID: " + id);
    }

    public static Platform getPlatformByName(String name) {
        for (Platform platform : Platform.values()) {
            if (platform.getName().equalsIgnoreCase(name)) {
                return platform;
            }
        }
        RiotApi.log.warning("Unknown platform name: " + name);
        throw new NoSuchElementException("Unknown platform name: " + name);
    }

    Platform(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return "https://" + getId().toLowerCase() + ".api.riotgames.com";
    }

    @Override
    public String toString() {
        return getId();
    }
}