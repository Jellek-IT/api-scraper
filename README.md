# Api Scraper

This project is a simple API scraper for [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a free fake REST API for testing and prototyping.
It fetches data from the /posts endpoint and saves each post as a separate JSON file, named using the post's ID (e.g., 1.json, 2.json, etc.).

## How to Run

This project requires JDK 17 to run, as it uses Spring Shell, which is based on Spring Boot 3.4.0.

1.  Build project:

```
./gradlew build -Dorg.gradle.java.home='<Path to JDK 17>'
```

2. run

```
java -jar apiscraper-0.0.1-SNAPSHOT.jar scrape
```

## Tests

There are two integration tests included in this project:

1. Verifies that JSON files are properly created for each post when using a mocked REST API client. Each file should be named with the corresponding post ID (e.g., 1.json, 2.json, etc.).
2. Ensures that when an exception occurs during the API call, the scraper displays an appropriate error message to the user.
