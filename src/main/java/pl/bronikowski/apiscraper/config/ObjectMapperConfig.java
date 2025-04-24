package pl.bronikowski.apiscraper.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Getter
    private static final ObjectMapper instance = new ObjectMapper();

    @Bean
    public ObjectMapper getObjectMapper() {
        return getInstance();
    }

    static {
        instance.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
