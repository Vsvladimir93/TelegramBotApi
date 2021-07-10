package com.petproject.TelegramBotApi.service.commands;

import com.petproject.TelegramBotApi.service.commands.implementations.ErrorFallbackCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner {

    private final CommandParser parser;
    private final Logger logger;
    private final ErrorFallbackCommand errorFallbackCommand;

    public CommandRunner(CommandParser parser, Logger logger, ErrorFallbackCommand errorFallbackCommand) {
        this.parser = parser;
        this.logger = logger;
        this.errorFallbackCommand = errorFallbackCommand;
    }

    public CommandResponse execute(String messageText) {
        Command command;

        try {
            command = parser.parse(messageText);
        } catch (NoSuchBeanDefinitionException e) {
            command = errorFallbackCommand;
        }

        return command.execute();
    }

}
