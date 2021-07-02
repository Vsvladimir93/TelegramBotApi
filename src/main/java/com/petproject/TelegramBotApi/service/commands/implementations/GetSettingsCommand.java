package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;

public class GetSettingsCommand implements Command {
    @Override
    public CommandResponse execute() {
        return () -> "Settings: {...}";
    }
}
