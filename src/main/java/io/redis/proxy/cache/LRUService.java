package io.redis.proxy.cache;

import io.redis.proxy.pojo.RedisResult;

/**
 * not a good design so far, all tomcat thread will be blocked by this service.
 * TODO: modified to timer async exec
 */
public class LRUService {

    private static LRUService m_service;
    private LRUCache<String, RedisResult> m_cache;

    // milli sec
    // TODO: inject later
    public final long CACHE_EXPIRE_TIME = 10000;

    // TODO: inject capacity later
    private LRUService(int capacity)
    {
        m_cache = new LRUCache<String, RedisResult>(capacity);
    }

    public static synchronized LRUService getInstance(int capacity)
    {
        if (null == m_service)
        {
            m_service = new LRUService(capacity);
        }

        return m_service;
    }

    public RedisResult get(String key)
    {
        synchronized (m_cache)
        {
            return m_cache.get(key);
        }
    }

    public void set(String key, RedisResult value)
    {
        synchronized (m_cache)
        {
            m_cache.set(key,value);
        }
    }
}
