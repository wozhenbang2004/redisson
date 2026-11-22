/**
 * Copyright (c) 2013-2026 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api.options;

import java.time.Duration;

/**
 *
 * @author Nikita Koksharov
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public final class MapCacheParams<K, V> extends BaseMapOptions<MapCacheOptions<K, V>, K, V> implements MapCacheOptions<K, V> {

    private final String name;
    private boolean removeEmptyEvictionTask;
    private long loaderTimeToLive;

    MapCacheParams(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public MapCacheOptions<K, V> removeEmptyEvictionTask() {
        removeEmptyEvictionTask = true;
        return this;
    }

    public boolean isRemoveEmptyEvictionTask() {
        return removeEmptyEvictionTask;
    }

    @Override
    public MapCacheOptions<K, V> loaderTimeToLive(Duration timeToLive) {
        long millis = timeToLive.toMillis();
        if (timeToLive.isNegative() || (!timeToLive.isZero() && millis == 0)) {
            throw new IllegalArgumentException("loaderTimeToLive must be zero or at least 1 ms");
        }
        loaderTimeToLive = millis;
        return this;
    }

    public long getLoaderTimeToLive() {
        return loaderTimeToLive;
    }
}
