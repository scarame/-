package generator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author zhuohao
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired

    @Value("${spring.redis.host}")
    private String host;

    @Autowired
    @Value("${spring.redis.port}")
    private int port;

    @Autowired
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Autowired
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Autowired
    @Value("${spring.redis.lettuce.pool.max-total}")
    private int maxTotal;

    @Autowired
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private long maxWaitMillis;

    /**
     * 连接池配置信息
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(maxIdle);
        //当池内没有可用连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        //其他属性可以自行添加
        return jedisPoolConfig;
    }

    /**
     * jedis连接工厂
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        //设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        //获得默认的连接池构造
        //这里需要注意的是，edisConnectionFactoryJ对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
        //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        //修改我们的连接池配置
        jpcf.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){
        return RedisCacheManager.create(connectionFactory);
    }
    /**
     * * 设置统一序列化
     * * @param factory
     * * @return
     * */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        //这里设置redis事务一致
        // template.setEnableTransactionSupport(true);
        return template;
    }
}