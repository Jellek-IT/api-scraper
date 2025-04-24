package pl.bronikowski.apiscraper

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.shell.test.ShellTestClient
import pl.bronikowski.apiscraper.exception.AppException
import pl.bronikowski.apiscraper.scraper.api.PostDto
import pl.bronikowski.apiscraper.scraper.internal.ApiRestClient
import pl.bronikowski.apiscraper.storage.internal.local.LocalStorageProperties
import spock.lang.Specification

@SpringBootTest
class BaseSpecification extends Specification {
    @Autowired
    ShellTestClient client

    @Autowired
    ObjectMapper objectMapper

    @SpringBean
    ApiRestClient apiRestClient = Mock()

    @SpringBean
    LocalStorageProperties localStorageProperties = Mock()

    def mockApiRestClient(List<PostDto> posts) {
        apiRestClient.getAllPosts() >> posts
    }

    def mockApiRestClientException() {
        apiRestClient.getAllPosts() >> {
            throw new AppException("execution exception")
        }
    }

    def mockLocalStorageProperties() {
        def fs = Jimfs.newFileSystem(Configuration.unix())
        localStorageProperties.getPath() >> fs.getPath("/data").toString()
    }
}
