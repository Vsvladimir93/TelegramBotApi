package com.petproject.TelegramBotApi.service;

import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    Logger logger;
    Environment env;

    public TelegramBot(Logger logger, Environment env) {
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
    }

}
