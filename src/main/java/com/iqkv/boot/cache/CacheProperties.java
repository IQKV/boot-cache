/*
 * Copyright 2024 IQKV.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.boot.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "iqkv.cache", ignoreUnknownFields = false)
public class CacheProperties {
  Ehcache ehcache = new Ehcache();

  public Ehcache getEhcache() {
    return ehcache;
  }

  public static class Ehcache {
    private int timeToLiveSeconds = 3600;  // 1 hour

    private long maxEntries = 100;

    public int getTimeToLiveSeconds() {
      return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(int timeToLiveSeconds) {
      this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public long getMaxEntries() {
      return maxEntries;
    }

    public void setMaxEntries(long maxEntries) {
      this.maxEntries = maxEntries;
    }
  }
}
