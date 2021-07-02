package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;

public class AddKeywordCommand implements Command {

    private String keyword;

    public AddKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse execute() {
        return null;
    }

}
