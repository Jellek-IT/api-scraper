package pl.bronikowski.apiscraper.scraper.internal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pl.bronikowski.apiscraper.scraper.api.PostDto;

import java.util.List;

@FeignClient(name = "api", url = "${app.api.url}")
public interface ApiRestClient {
    /* todo: In future could be paged with _limit and _page query parameters.
        Total elements count is present in x-total-count header */
    @GetMapping("/posts")
    List<PostDto> getAllPosts();
}
