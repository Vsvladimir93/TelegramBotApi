package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;

public class SetTimeCommand implements Command {

    private String time;

    public SetTimeCommand(String time) {
        this.time = time;
    }

    @Override
    public CommandResponse execute() {
        return null;
    }
}
