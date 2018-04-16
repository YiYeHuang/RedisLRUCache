package io.redis.proxy.controller;

import io.redis.proxy.cache.LRUService;
import io.redis.proxy.pojo.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.redis.proxy.pojo.RedisResult;

import java.util.Date;

@RestController
public class RedisRestController
{

	@Autowired
	@Qualifier("RedisTemplate")
	private RedisTemplate<String, Object> template;

	// TODO: inject
	private LRUService service = LRUService.getInstance(100);

	@GetMapping("search/{key}")
	public RedisResult getSingleValue(@PathVariable("key") String key){

		RedisResult cacheValue = service.get(key);

		// CASE: no find in cache
		if (cacheValue == null)
		{
			String value = (String)this.template.opsForValue().get(key);
			// CASE: no find in DB
			if (null == value)
			{
				return new RedisResult(State.NO_VALUE, key, null, null);
			}
			// CASE: find in DB
			else
			{
			    RedisResult new_cache = new RedisResult(State.DB_READ, key, value, new Date());
			    // register in cache
				service.set(key, new_cache);
				return new_cache;
			}
		}
		// CASE: find in cache
		else
		{
            Long diff = new Date().getTime() - cacheValue.getDate().getTime();

            if (diff > service.CACHE_EXPIRE_TIME)
            {
                // Invalid cache, read from db again
                String value = (String)this.template.opsForValue().get(key);
                RedisResult new_cache = new RedisResult(State.DB_READ, key, value, new Date());
                // register in cache
                service.set(key, new_cache);
                return new_cache;
            }
            else
            {
                cacheValue.setState(State.CACHE_READ);
                return cacheValue;
            }
		}
	}

	@PostMapping("insert/{key}/{value}")
	public RedisResult addValue(@PathVariable("key") String key, @PathVariable("value") String value){
		RedisResult result = new RedisResult(
				State.NEW_DATA,
				key,
				value,
				new Date());
		
		this.template.opsForValue().set(key, value);
		return result;
	}
}
