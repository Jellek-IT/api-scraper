package pl.bronikowski.apiscraper.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import pl.bronikowski.apiscraper.ApiscraperApplication;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = ApiscraperApplication.class)
public class PropertiesConfig {
}
