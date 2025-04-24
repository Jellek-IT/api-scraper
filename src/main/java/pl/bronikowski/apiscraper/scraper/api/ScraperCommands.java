package pl.bronikowski.apiscraper.scraper.api;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import pl.bronikowski.apiscraper.scraper.internal.ScraperService;

@Command
@RequiredArgsConstructor
public class ScraperCommands {
    private final ScraperService scraperService;

    @Command(command = "scrape", description = "Scrapes api with url provided in application configuration")
    public String scrape() {
        return scraperService.scrape();
    }
}
