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
     * */
    @Test
    public void testRedisStrSet(){
        Jedis jedis = new Jedis("redis://user:foobared@127.0.0.1:6379/2");
        jedis.set("jedis_test_manner4","ceshi4");
        jedis.set("jedis_test_manner4","ceshi5","xx");
        jedis.set("jedis_test_manner4","ceshi6","xx","px",10*1000);
        System.out.println(jedis.get("jedis_test_manner4"));
    }
}
