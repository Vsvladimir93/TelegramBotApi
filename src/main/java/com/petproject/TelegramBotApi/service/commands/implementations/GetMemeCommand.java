package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("get_meme")
@Scope("prototype")
public class GetMemeCommand implements Command {

    @Override
    public CommandResponse execute() {
        return () -> "Get meme command work.";
    }

}
