package pl.bronikowski.apiscraper.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import pl.bronikowski.apiscraper.ApiscraperApplication;

@Configuration
@EnableFeignClients(basePackageClasses = ApiscraperApplication.class)
public class FeignConfig {
}
