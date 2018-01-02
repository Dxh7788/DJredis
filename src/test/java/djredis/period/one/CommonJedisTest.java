package djredis.period.one;

import com.sun.jndi.toolkit.url.Uri;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.net.MalformedURLException;

/**
 * without spring container,test the Jedis samples
 * @author xh.d
 * @date 2017/12/29 15:45
 */
public class CommonJedisTest {
    /**
     * there are several types of Jedis client's connection.
     * first.it can use ip or Uri to process connection.
     * We can use 'localhost' to go a development connection.Others like '192.168.0.109' etc.
     * */
    @Test
    public void testCon(){
        /**
         * common config,require host/auth password
         * 1.connection manner 1
         * host=localtest or ips like 127.0.0.1
         * port = 6379 ,6379 is the default port
         * the constructor is Jedis(host,port)
         * */
        /*Jedis jedis = new Jedis("localhost");
        jedis.auth("foobared");
        jedis.set("jedis_test","ceshi");*/
        /**
         * 1.1 connection manner 2
         * */
        /*Jedis jedis1 = new Jedis("192.168.0.109");
        jedis1.auth("foobared");
        jedis1.set("jedis_test_manner2","ceshi2");*/
        /**
         * 2.connection manner 2
         * connected the redis client with the given URI started with scheme "redis" like 'redis://www.htouhui.com/redis-cluster'
         * 2.1  Jedis jedis2 = new Jedis("redis://127.0.0.1:6379/0");
         *      jedis2.auth("foobared");
         * 2.2  Jedis jedis2 = new Jedis("redis://user:foobared@127.0.0.1:6379/0");
         * */
        Jedis jedis0 = new Jedis("redis://user:foobared@127.0.0.1:6379/2");
        String value = jedis0.set("jedis_test_manner4","ceshi4");
    }
    /**
     *
     * Redis's afford String,List,ZSet,Hash,HyperLogLog and etc.
     * set/get is the string commands
     * set method with whole params,set(String key,String value,String nxxx,String expx,long time)
     * how to explain set(String key,String value,String nxxx,String expx,long time):
     * <key,value>:redis key and the value reflect to the key
     * nxxx:if nxxx equals to 'NX',set the key 'key' with the value 'value' when the key is not exist.if nxxx equals to 'XX' ,Set the key 'key' with value 'value' when the key is exists.
     * param expx represents the unit that going to be used. case expx= 'EX',the unit is seconds,case expx = 'PX',the unit is milliseconds.
     * note:NX ,XX,EX,PX is not case-sensitive.You can also use the nx,xx,ex and px.
     *
     * public static enum Command {SET/GET/GETSET/SETRANGE/GETRANGE/SETBIT/GETBIT/STRLEN/INCR/DECR/APPEND/MSET/MGET/MSETNX/MGETNX/PSETEX/SETNX/INCYBY/DECRBY}
     * String commands includes:set/get/getset/getrange
     * */
    @Test
    public void testRedisStrSetAndGet(){
        Jedis jedis = new Jedis("redis://user:foobared@127.0.0.1:6379/2");
        jedis.set("jedis_test_manner4","ceshi4");
        jedis.set("jedis_test_manner4","ceshi5","xx");
        jedis.set("jedis_test_manner4","ceshi6","xx","px",10*1000);
//        jedis.setbit("jedis_test_manner4",4,"1");
        jedis.set("jedis_test_manner4_num","10");
        System.out.println(jedis.strlen("jedis_test_manner4"));
        System.out.println(jedis.type("jedis_test_manner4_num"));
        System.out.println(jedis.append("jedis_test_manner4","aaa"));
        System.out.println(jedis.getrange("jedis_test_manner4",0,-1));
        System.out.println(jedis.mset(new String[]{"jedis_test_manner4_num","aaa","jedis_test_manner4","bbb"}));
        System.out.println(jedis.mget(new String[]{"jedis_test_manner4_num","jedis_test_manner4"}));
    }
    /**
     * Redis list commands.
     * List Commands includes:[lpush/lpushx/lpop/llen/lindex/linsert/lrange/lset/lrem/ltrim]
     * */
    @Test
    public void testRedisList(){
        Jedis jedis = new Jedis("redis://user:foobared@127.0.0.1:6379/2");
        /*jedis.lpush("list_test_key1","aaa");
        jedis.lpush("list_test_key1","aab");
        jedis.lpush("list_test_key1","aac");*/
        System.out.println(jedis.lindex("list_test_key1",1));
        System.out.println(jedis.ltrim("list_test_key1",0,0));
        System.out.println(jedis.llen("list_test_key1"));
    }
    /**
     * Redis Hash Commands
     * Hash Commands includes:[hset/hget/hmset/hmget/exists/getall/incr/incrByFloat/hkeys/hlen/hscan/hsetnx/hvals/hdel]
     * */
    @Test
    public void testRedisHash(){
        Jedis jedis = new Jedis("redis://user:foobared@127.0.0.1:6379/2");
        /**
         * 插入
         * */
        /*jedis.hset("hash_test_key1","1","hash1");
        jedis.hset("hash_test_key1","2","hash2");*/
        System.out.println(jedis.hget("hash_test_key1","1"));
        System.out.println(jedis.hmget("hash_test_key1",new String[]{"1","2"}));
        /*jedis.hdel();
        jedis.hexists();
        jedis.hget();
        jedis.hgetAll();
        jedis.hincrBy();
        jedis.hincrByFloat();
        jedis.hkeys();
        jedis.hlen();
        jedis.hmget();
        jedis.hmset();
        jedis.hscan();
        jedis.hset();
        jedis.hget();
        jedis.hsetnx();
        jedis.hvals();*/
    }
    @Test
    public void testRedisZSet(){
        Jedis jedis = new Jedis("redis://user:foobared@127.0.0.1:6379/2");
        /*jedis.zadd();
        jedis.zcard();
        jedis.zcount();
        jedis.zincrby();
        jedis.zinterstore();
        jedis.zlexcount();
        jedis.zrange();
        jedis.zrangeByLex();
        jedis.zrangeByScore();
        jedis.zrangeByScoreWithScores();
        jedis.zrank();
        jedis.zrem();
        jedis.zremrangeByRank();
        jedis.zrevrange();
        jedis.zrevrangeByLex();
        jedis.zrevrangeByScore();
        jedis.zrevrangeByScoreWithScores();
        jedis.zscore();*/
    }
}
