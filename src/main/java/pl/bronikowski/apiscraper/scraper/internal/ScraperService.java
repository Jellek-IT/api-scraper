package pl.bronikowski.apiscraper.scraper.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bronikowski.apiscraper.storage.api.StorageClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScraperService {
    private final ApiRestClient apiRestClient;
    private final StorageClient storageClient;

    public String scrape() {
        log.info("Waiting for api response...");
        var posts = apiRestClient.getAllPosts();
        log.info("Saving result to files...");
        storageClient.save(posts);
        return "saved " + posts.size() + " posts";
    }
}
