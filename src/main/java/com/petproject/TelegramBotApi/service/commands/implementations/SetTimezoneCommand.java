package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("set_timezone")
public class SetTimezoneCommand implements Command {
    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String arguments) {
        return () -> "Set timezone command work.";
    }

}
