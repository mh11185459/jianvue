package com.dev.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	private RedisUtil(){
		
	}
	/**
	 * 最大空闲数
	 */
	private static int maxIdle = 10;
	
	/**
	 * 最大连接数
	 */
	private static int maxTotal = 10;

	/**
	 * 最大空闲数
	 */
	private static long  maxWaitMillis = 1000l;
	
	/**
	 * IP地址
	 */
	private static String address = "192.168.142.128";

	/**
	 * 端口
	 */
	private static int port = 6379;
	
	private static JedisPool pool;
	
	public static synchronized Jedis getJedis(){
		if(pool==null){
			JedisPoolConfig  config = new JedisPoolConfig();
			config.setMaxIdle(maxIdle);
			config.setMaxTotal(maxTotal);
			config.setMaxWaitMillis(maxWaitMillis);
			config.setTestOnBorrow(true);
			pool = new JedisPool(config,address,port);
		}		
		return pool.getResource();		
	}
	
	/**
	 * 释放资源
	 * @param jedis
	 */
	public static void returnResource(Jedis jedis){
		if(null != jedis){
			pool.returnResourceObject(jedis);
		}
    }	
}
