package djredis.period.one;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * without spring container,test the Jedis samples
 * @author xh.d
 * @date 2017/12/29 15:45
 */
public class CommonJedisTest {
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
