package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;

public class ErrorFallbackCommand implements Command {

    @Override
    public CommandResponse execute() {
        return () -> "Something went wrong.";
    }
}
