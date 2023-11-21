package ru.wyrd.limiter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@AutoConfiguration
@EnableConfigurationProperties(LimiterProperties.class)
@ConditionalOnProperty("limiter.enabled")
public class LimiterConfiguration {

    @Bean
    public Cache<String, Integer> requestCache(LimiterProperties properties) {
        return CacheBuilder.newBuilder()
                .maximumSize(properties.cacheMaxSize())
                .expireAfterWrite(properties.expireInSeconds(), TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public Limiter limiter(Cache<String, Integer> cache, LimiterProperties properties) {
        return new Limiter(cache, properties);
    }
}
