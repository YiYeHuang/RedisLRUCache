package io.redis.proxy.pojo;

import java.util.Date;

public class RedisResult {
	private State state;
	private String key;
	private String value;
	private Date inCacheTimeStamp;


	public RedisResult(State state, String key, String value, Date creationTime ) {
		this.state = state;
		this.key = key;
		this.value = value;
		this.inCacheTimeStamp = creationTime;
	}

	public State getState() {
		return state;
	}

	public String getKey() {
		return key;
	}

	public Date getDate()
	{
		return this.inCacheTimeStamp;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setState(State currState)
	{
		this.state = currState;
	}
}
