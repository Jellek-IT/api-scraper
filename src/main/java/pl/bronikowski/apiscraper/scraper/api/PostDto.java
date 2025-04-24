package pl.bronikowski.apiscraper.scraper.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.bronikowski.apiscraper.storage.api.StorageObject;

import java.util.UUID;

public record PostDto(
        Long id,
        Long userId,
        String title,
        String body
) implements StorageObject {
    @Override
    @JsonIgnore
    public String getFileName() {
        return id != null ? id.toString() : "undefined_" + UUID.randomUUID();
    }
}
