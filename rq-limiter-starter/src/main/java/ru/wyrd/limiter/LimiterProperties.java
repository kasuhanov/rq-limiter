package ru.wyrd.limiter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "limiter")
public record LimiterProperties(
        boolean enabled,
        int maxTries,
        int cacheMaxSize,
        int expireInSeconds) {
}
