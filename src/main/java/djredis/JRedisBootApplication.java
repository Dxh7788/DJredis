package djredis;

import djredis.config.JRedisGlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Import(JRedisGlobalConfig.class)
public class JRedisBootApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(JRedisBootApplication.class,args);
    }
}
