package pl.bronikowski.apiscraper.config.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.annotation.CommandScan;
import pl.bronikowski.apiscraper.ApiscraperApplication;

@Configuration
@CommandScan(basePackageClasses = ApiscraperApplication.class)
public class CommandConfig {
    @Bean
    CommandExceptionResolver customExceptionResolver() {
        return new AppCommandExceptionResolver();
    }
}
