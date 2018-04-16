package io.redis.proxy.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * As read and write depends on the entire LinkedHashMap, throughput is bac
 * TODO: change to currentHashMap + some current linkedList
 */
public class LRUCache<K,V> {

    private LinkedHashMap<K, V> map;
    private final int CAPACITY;

    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<K, V>(capacity, 0.75f, true)
        {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }

    public V get(K key) {
        return map.getOrDefault(key, null);
    }

    public void set(K key, V value) {
        map.put(key, value);
    }
}
