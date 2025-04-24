package pl.bronikowski.apiscraper.storage.internal.local;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.local-storage")
public class LocalStorageProperties {
    private String path;
}
