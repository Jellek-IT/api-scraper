package pl.bronikowski.apiscraper.scraper.api

import org.springframework.shell.test.autoconfigure.AutoConfigureShell
import org.springframework.shell.test.autoconfigure.AutoConfigureShellTestClient
import org.springframework.test.annotation.DirtiesContext
import pl.bronikowski.apiscraper.BaseSpecification

import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

import static org.awaitility.Awaitility.await

@AutoConfigureShell
@AutoConfigureShellTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ScraperCommandsTest extends BaseSpecification {

    def "should scrape api and save posts data to files"() {
        given: "api rest client mock and local storage properties mock"
        var post1 = new PostDto(1, 1, "title 1", "body 1")
        var post2 = new PostDto(2, 1, "title 2", "body 2")
        mockApiRestClient(List.of(post1, post2))
        mockLocalStorageProperties()

        when: "session for scrape command is created"
        var session = client.nonInterative("scrape").run()

        then: "should create post files and return saved posts count"
        await().atMost(3, TimeUnit.SECONDS).untilAsserted {
            var post1Path = Paths.get(localStorageProperties.getPath() + "/posts/1.json")
            var post2Path = Paths.get(localStorageProperties.getPath() + "/posts/2.json")
            assert session.screen().lines().any { it.contains("saved 2 posts") }
            assert Files.exists(post1Path)
            assert Files.exists(post2Path)
            assert objectMapper.readValue(post1Path.toFile(), PostDto) == post1
            assert objectMapper.readValue(post2Path.toFile(), PostDto) == post2
        }
    }

    def "Should return error when api throws exception"() {
        given: "api rest client mock"
        mockApiRestClientException()

        when: "session for scrape command is created"
        var session = client.nonInterative("scrape").run()

        then: "should return exception details"
        await().atMost(3, TimeUnit.SECONDS).untilAsserted {
            assert session.screen().lines().any { it.contains("execution exception") }
        }

    }
}
