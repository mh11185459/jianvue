package com.dev.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	private RedisUtil(){
		
	}
	/**
	 * ��������
	 */
	private static int maxIdle = 10;
	
	/**
	 * ���������
	 */
	private static int maxTotal = 10;

	/**
	 * ��������
	 */
	private static long  maxWaitMillis = 1000l;
	
	/**
	 * IP��ַ
	 */
	private static String address = "192.168.142.128";

	/**
	 * �˿�
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
	 * �ͷ���Դ
	 * @param jedis
	 */
	public static void returnResource(Jedis jedis){
		if(null != jedis){
			pool.returnResourceObject(jedis);
		}
    }	
}
