package com.dev.redis;
import redis.clients.jedis.Jedis;;

public class App {
	
	public static void main(String[] args){
		Jedis jedis = new Jedis("192.168.142.128",6379);
		System.out.println(jedis.ping());
		jedis.set("123","ok");
		System.out.println(jedis.get("123"));
	
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(jedis.get("123"));
	}
}