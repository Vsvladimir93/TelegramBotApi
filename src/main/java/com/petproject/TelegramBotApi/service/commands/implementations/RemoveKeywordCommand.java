package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("remove_keyword")
@Scope("prototype")
public class RemoveKeywordCommand implements Command {

    private String keyword;

    public RemoveKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse execute() {
        return () -> "Remove keyword command work.";
    }
}
