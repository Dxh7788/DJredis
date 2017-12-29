package djredis.period.one;

import org.junit.Test;
import redis.clients.jedis.Jedis;

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
         * */
        Jedis jedis = new Jedis("localhost");
        jedis.auth("foobared");
        jedis.set("jedis_test","ceshi");
    }
}
