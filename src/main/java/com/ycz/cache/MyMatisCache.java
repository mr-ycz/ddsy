package com.ycz.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyMatisCache implements Cache {

    //获取当前缓存的namespace
    private String id;
    //提供一个可重入锁
    private ReadWriteLock lock=new ReentrantReadWriteLock();

    public MyMatisCache(){}

    public MyMatisCache(String id){
        this.id=id;
        System.out.println(".....id:"+id);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println(".....Mybatis putObject.....");
        RedisTemplate<String,Object> template = (RedisTemplate<String, Object>) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
        //此处加toString是因为redisTemplate中,key必须是String
        template.opsForValue().set(key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println(".....Mybatis getObject.....");
        System.out.println("key:"+key);

        RedisTemplate<String,Object> template = (RedisTemplate<String, Object>) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
        //此处加toString是因为redisTemplate中,key必须是String
        Object o = template.opsForValue().get(key.toString());

        return o;
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println(".....Mybatis removeObject.....");

        RedisTemplate<String,Object> template = (RedisTemplate<String, Object>) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
        template.delete(key.toString());//此处加toString是应为参数必须是toString

        return null;
    }

    @Override
    public void clear() {
        System.out.println(".....Mybatis clear.....");

        RedisTemplate<String,Object> template = (RedisTemplate<String, Object>) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");
        //获得所有的key
        Set<String> keys = template.keys("*" + getId() + "*");
        //批量删除
        template.delete(keys);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }
}
