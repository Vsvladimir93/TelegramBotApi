package com.petproject.TelegramBotApi.service.commands;

import com.petproject.TelegramBotApi.exceptions.CommandException;
import com.petproject.TelegramBotApi.service.commands.implementations.ErrorFallbackCommand;
import com.petproject.TelegramBotApi.service.commands.implementations.NoSuchCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CommandRunner {

    private final CommandParser parser;
    private final CommandTypeResolver typeResolver;
    private final Logger logger;

    public CommandRunner(CommandParser parser, CommandTypeResolver typeResolver, Logger logger) {
        this.parser = parser;
        this.typeResolver = typeResolver;
        this.logger = logger;
    }

    public CommandResponse execute(DefaultAbsSender bot, Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText())
            return () -> "";

        Command command;
        CommandParser.PairedResult result = parser.parse(update.getMessage().getText());

        try {
            command = typeResolver.getCommandByString(result.command);
        } catch (NoSuchBeanDefinitionException e) {
            command = new NoSuchCommand();
        } catch (Exception e) {
            logger.error("", e);
            command = new ErrorFallbackCommand();
        }

        try {
            return command.execute(bot, update, result.arguments);
        } catch (CommandException e) {
            logger.error("CommandException: {}", e.getMessage());
            return e::getMessage;
        } catch (Exception e) {
            logger.error("", e);
            return () -> "Something went wrong";
        }
    }

}
