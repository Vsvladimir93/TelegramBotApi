package com.petproject.TelegramBotApi.service.commands;

import com.petproject.TelegramBotApi.service.commands.implementations.ErrorFallbackCommand;
import com.petproject.TelegramBotApi.service.commands.implementations.NoSuchCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner {

    private final CommandParser parser;
    private final Logger logger;

    public CommandRunner(CommandParser parser, Logger logger) {
        this.parser = parser;
        this.logger = logger;
    }

    public CommandResponse execute(String messageText) {
        Command command;

        try {
            command = parser.parse(messageText);
        } catch (NoSuchBeanDefinitionException e) {
            command = new NoSuchCommand();
        } catch (Exception e) {
            logger.error("", e);
            command = new ErrorFallbackCommand();
        }

        return command.execute();
    }

}
