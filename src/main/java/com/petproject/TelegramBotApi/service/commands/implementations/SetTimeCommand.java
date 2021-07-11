package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("set_time")
public class SetTimeCommand implements Command {
    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String arguments) {
        return () -> "Set time command work.";
    }
}
