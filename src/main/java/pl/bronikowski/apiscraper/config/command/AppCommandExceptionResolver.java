package pl.bronikowski.apiscraper.config.command;

import feign.FeignException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import pl.bronikowski.apiscraper.exception.AppException;

public class AppCommandExceptionResolver implements CommandExceptionResolver {
    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if (ex instanceof AppException appException) {
            return CommandHandlingResult.of("Exception during execution: " + appException.getMessage());
        }
        if (ex instanceof FeignException feignException) {
            return CommandHandlingResult.of("Exception during execution: " + feignException.getMessage());
        }
        return null;
    }
}
