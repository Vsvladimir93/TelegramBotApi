package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorFallbackCommand implements Command {

    private final String message;

    public ErrorFallbackCommand(String message) {
        this.message = message;
    }

    public ErrorFallbackCommand() {
        message = "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String arguments) {
        return () -> message.isBlank() ? "Something went wrong." : message;
    }
}
