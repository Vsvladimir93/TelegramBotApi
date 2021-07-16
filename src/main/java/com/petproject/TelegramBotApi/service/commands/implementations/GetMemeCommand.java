package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("get_meme")
public class GetMemeCommand implements Command {

    @Override
    public String getDescription() {
        return "/get_meme - return new meme";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String arguments) {
        return () -> "Get meme command work.";
    }

}
