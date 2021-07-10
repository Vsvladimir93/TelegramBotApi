package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;

@Component("error_fallback")
public class ErrorFallbackCommand implements Command {

    @Override
    public CommandResponse execute() {
        return () -> "Something went wrong.";
    }
}
