package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;

public class SetTimezoneCommand implements Command {

    private String timezone;

    public SetTimezoneCommand(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public CommandResponse execute() {
        return null;
    }
}
