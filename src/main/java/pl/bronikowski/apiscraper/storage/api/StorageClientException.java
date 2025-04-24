package pl.bronikowski.apiscraper.storage.api;

import pl.bronikowski.apiscraper.exception.AppException;

public class StorageClientException extends AppException {
    public StorageClientException(String message) {
        super(message);
    }
}
