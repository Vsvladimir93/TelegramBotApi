package com.petproject.TelegramBotApi.service;

import com.petproject.TelegramBotApi.service.commands.CommandParser;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import com.petproject.TelegramBotApi.service.commands.CommandRunner;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class RandomMemeTelegramBot extends TelegramLongPollingBot {

    Logger logger;
    Environment env;

    public RandomMemeTelegramBot(Logger logger, Environment env) {
        this.logger = logger;
        this.env = env;
    }

    @Override
    public String getBotUsername() {
        return env.getProperty("bot.name");
    }

    @Override
    public String getBotToken() {
        return env.getProperty("bot.token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Update {} received. Message: {}", update.getUpdateId(), update.getMessage());

        if (!update.getMessage().hasText()) {
            logger.warn("Update message has no text.");
            return;
        }

        String messageText = update.getMessage().getText();

        CommandRunner runner = new CommandRunner(new CommandParser(messageText));
        CommandResponse response = runner.execute();
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text(response.getResponseMessage())
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("", e);
        }

    }

}
