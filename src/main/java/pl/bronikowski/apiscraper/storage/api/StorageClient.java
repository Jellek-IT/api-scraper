package pl.bronikowski.apiscraper.storage.api;

import pl.bronikowski.apiscraper.scraper.api.PostDto;

import java.util.List;

public interface StorageClient {
    void save(List<PostDto> objects);
}
