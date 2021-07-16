package com.petproject.TelegramBotApi.service.commands;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    CommandResponse execute(DefaultAbsSender bot, Update update, String arguments);

    String getDescription();
}
