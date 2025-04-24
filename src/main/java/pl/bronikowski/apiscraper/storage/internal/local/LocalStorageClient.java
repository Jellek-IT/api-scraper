package pl.bronikowski.apiscraper.storage.internal.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bronikowski.apiscraper.scraper.api.PostDto;
import pl.bronikowski.apiscraper.storage.api.StorageClient;
import pl.bronikowski.apiscraper.storage.api.StorageClientException;
import pl.bronikowski.apiscraper.storage.api.StorageObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalStorageClient implements StorageClient {
    private static final String POSTS_FOLDER_NAME = "posts";
    private final ObjectMapper objectMapper;
    private final LocalStorageProperties localStorageProperties;

    @Override
    public void save(List<PostDto> postDtos) {
        createFolder(POSTS_FOLDER_NAME);
        postDtos.parallelStream()
                .forEach(postDto -> save(postDto, POSTS_FOLDER_NAME));
    }

    private void save(StorageObject storageObject, String folder) {
        var name = storageObject.getFileName();
        var path = getFolderPath(folder) + File.separator + name + ".json";
        var file = new File(path);
        try {
            objectMapper.writeValue(file, storageObject);
        } catch (Exception e) {
            throw new StorageClientException("Could not save file with name=" + name);
        }
    }

    private void createFolder(String folder) {
        var folderPath = getFolderPath(folder);
        var directory = new File(folderPath);
        if (directory.exists()) {
            return;
        }
        try {
            Files.createDirectories(Paths.get(folderPath));
        } catch (Exception e) {
            throw new StorageClientException("Could not create folder with path=" + folderPath);
        }
    }

    private String getFolderPath(String folder) {
        return localStorageProperties.getPath() + File.separator + folder;
    }
}
