package com.jztey.exam;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jztey.framework.cache.SpelCacheNameCacheResolver;

@Configuration
@EnableCaching
public class ApplicationCaching implements CachingConfigurer {
    public static final String SECONDARY_CACHE_RESOLVER = "secondaryCacheResolver";

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(new EhCacheManagerFactoryBean().getObject());
    }

  

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new SpelCacheNameCacheResolver(cacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }
}
