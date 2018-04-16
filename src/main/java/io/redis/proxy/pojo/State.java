package io.redis.proxy.pojo;

public enum State {
    CACHE_READ,
    DB_READ,
    NO_VALUE,
    NEW_DATA
}
